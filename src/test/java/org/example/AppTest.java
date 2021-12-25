package org.example;

import dataProviders.Book;
import dataProviders.mySql.DataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private static final Logger log = LogManager.getLogger(AppTest.class);

    dataProviders.mySql.DataProvider dataProvider = new dataProviders.mySql.DataProvider();
    Book new_book_1 = new Book( 1, "Taras Bulba", "Russia");
    Book new_book_2 = new Book( 2, "Books English", "UK");

    @Before
    public void insert(){
        drop();
        dataProvider.insert(new_book_1);
        dataProvider.insert(new_book_2);
    }

    public void drop(){
        DataProvider.dropData();
    }

    @Test
    public void getByIdPositive(){
        Book byId = dataProvider.getById(new_book_1.getId());
        assertEquals(new_book_1.getId(), byId.getId());
    }

    @Test
    public void getByIdNegative(){
        Book byId = dataProvider.getById(System.currentTimeMillis());
        assertNotEquals(new_book_1.getId(), byId.getId());
    }

    @Test
    public void select(){
        List<Book> entities;
        entities = dataProvider.selectBooks();
        assertEquals(2, entities.size());
    }

    @Test
    public void delete(){
        Book forDelete = new Book();
        dataProvider.delete(2);
        assertEquals(forDelete, dataProvider.getById(2));
    }

    @Test
    public void updatePositive(){
        Book newEntity = new Book(2,"newBookName", "newCountry");
        dataProvider.update(newEntity);
        assertEquals(newEntity, dataProvider.getById(newEntity.getId()));
    }

    @Test
    public void updateNegative(){
        Book newEntity = new Book(System.currentTimeMillis(), "newBookName", "newCountry");
        dataProvider.update(newEntity);
        assertNotEquals(newEntity, dataProvider.getById(newEntity.getId()));
    }
    /*
    @Test
    public void insert(){
        Book newBook = new Book("Taras Bulba", "Russia");
        Book newBook1 = new Book("Bet Book", "UK");
        dataProvider.insert(newBook);
        dataProvider.insert(newBook1);
    }

    @Test
    public void select(){
        Book newBook = new Book("Bet Book", "UK");
        dataProvider.insert(newBook);
        List<Book> newBookList = dataProvider.selectBooks();
        assertEquals(newBook, newBookList.get(newBookList.size()-1));
    }

    @Test
    public void getById(){
        Book newBook = new Book("Bet Book", "UK");
        dataProvider.insert(newBook);
        Book byId = dataProvider.getById(newBook.getId());
        assertEquals(newBook, byId);
    }

    @Test
    public void update(){
        Book book = new Book("Bet Book", "UK");
        Book newBook = new Book("War and World", "Russia");
        dataProvider.insert(book);
        dataProvider.update(newBook);
        Book byId = dataProvider.getById(newBook.getId());
        assertEquals(newBook, byId);
    }
    @Test
    public void delete() {
        Book newBook = new Book("Bet Book", "UK");
        dataProvider.insert(newBook);
        dataProvider.delete(newBook.getId());
    }
*/
}
