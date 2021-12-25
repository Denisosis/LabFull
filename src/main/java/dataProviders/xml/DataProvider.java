package dataProviders.xml;

import dataProviders.Book;
import dataProviders.IDataProvider;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.AbstractDataProvider;
import org.example.ConstantsProperties;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class DataProvider extends AbstractDataProvider {
    private static final Logger log = LogManager.getLogger(DataProvider.class);

    @Override
    public Book getById(long id) {
        List<Book> books = selectBooks();
        Book book = null;
        try {
            book = books.stream().filter(a -> a.getId() == id).collect(Collectors.toList()).get(0);
        } catch (IndexOutOfBoundsException e){
            log.catching(e);
        }
        return book;
    }

    @Override
    public List<Book> selectBooks() {
        Books books = new Books();
        try{
            JAXBContext context = JAXBContext.newInstance(Books.class);
            books = (Books) context.createUnmarshaller().unmarshal(new File(ConstantsProperties.PATH_TO_XML));
        } catch (JAXBException e){
            log.catching(e);
        }
        return books.getBooks();
    }

    @Override
    public void insert(Book book) {
        File file = new File(ConstantsProperties.PATH_TO_XML);
        JAXBContext context;
        Books books = new Books();
        List<Book> bookList = new ArrayList<>();
        Marshaller marshaller;
        if(file.exists()) {
            try {
                context = JAXBContext.newInstance(Books.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                books = (Books) unmarshaller.unmarshal(new File(ConstantsProperties.PATH_TO_XML));
                bookList = books.getBooks();
                bookList.add(book);
                books.setBooks(bookList);
                marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                marshaller.marshal(books, new File(ConstantsProperties.PATH_TO_XML));
            } catch (JAXBException e) {
                log.catching(e);
            }
        } else {
            try {
                context = JAXBContext.newInstance(Books.class);
                bookList.add(book);
                books.setBooks(bookList);
                marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                marshaller.marshal(books, new File(ConstantsProperties.PATH_TO_XML));
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(long id) {
        Book book = getById(id);
        if(book == null){
            return;
        }
        JAXBContext context;
        Books books = new Books();
        Marshaller marshaller;
        List<Book> bookList = selectBooks();
        bookList.removeIf(book1 -> (book1.getId() == id));
        books.setBooks(bookList);
        try {
            context = JAXBContext.newInstance(Books.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(books, new File(ConstantsProperties.PATH_TO_XML));
        } catch (JAXBException e) {
            log.catching(e);
        }
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
}
