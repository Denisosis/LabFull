package org.example;


import dataProviders.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.List;

public abstract class AbstractDataProvider {
    private static final Logger log = LogManager.getLogger(AbstractDataProvider.class);

    public abstract Book getById(long id);

    public abstract List<Book> selectBooks();

    public abstract void insert(Book book);

    public abstract void delete(long id);

    public abstract void update(Book book);
}
