package dataProviders.xml;

import dataProviders.Book;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.example.ConstantsProperties;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = ConstantsProperties.BOOKS)

public class Books {
    private List<Book> books = new ArrayList<>();
    public List<Book> getBooks() {
        return books;
    }
    @XmlElement(name = ConstantsProperties.BOOK)
    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return books.toString();
    }
}
