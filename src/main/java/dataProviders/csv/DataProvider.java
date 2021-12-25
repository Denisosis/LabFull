package dataProviders.csv;

import com.opencsv.bean.CsvToBeanBuilder;

import dataProviders.DataProviderTools;
import dataProviders.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.ConstantsProperties;
import org.example.AbstractDataProvider;
import org.example.enums.Status;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataProvider extends AbstractDataProvider {
    private static final Logger log = LogManager.getLogger(DataProvider.class);


    @Override
    public Book getById(long id) {
        List<Book> books = selectBooks();
        Book Book = null;
        try {
            Book = books.stream().filter(a -> a.getId() == id).collect(Collectors.toList()).get(0);
        } catch (IndexOutOfBoundsException e) {
            log.error(e);
        }
        return Book;
    }


    @Override
    public List<Book> selectBooks() {
    List<Book> books = null;
        try {
        books = new CsvToBeanBuilder<Book>(new FileReader(ConstantsProperties.PATH_TO_CSV))
                .withType(Book.class).build().parse();
    } catch (FileNotFoundException e) {
        log.error(e);
    }
        return books;
    }



    @Override
    public void insert(Book book) {
        List<Object> books = new ArrayList<>();
        if (DataProviderTools.readCSV(Book.class, ConstantsProperties.PATH_TO_CSV).isPresent()) {
            books = DataProviderTools.readCSV(Book.class, ConstantsProperties.PATH_TO_CSV).get();
        }
        books.add(book);
        DataProviderTools.saveCSV(books, ConstantsProperties.PATH_TO_CSV);
        DataProviderTools.saveHistoryContent(getClass().getName(), Status.SUCCESS, book);
    }


    @Override
    public void update(Book book) {
        Book byId = getById(book.getId());
        if (byId == null){
            return;
        }
        delete(book.getId());
        insert(book);
    }

    @Override
    public void delete(long id) {
        Book newBook = getById(id);
        if (newBook == null) {
            return;
        }
        List<Book> books = new ArrayList<>();
        if (DataProviderTools.readCSV(Book.class, ConstantsProperties.PATH_TO_CSV).isPresent()) {
            books = (List<Book>) (Object) DataProviderTools.readCSV(Book.class, ConstantsProperties.PATH_TO_CSV).get();
        }
        books.removeIf(book -> (book.getId() == id));
        DataProviderTools.saveCSV(books, ConstantsProperties.PATH_TO_CSV);
    }
}