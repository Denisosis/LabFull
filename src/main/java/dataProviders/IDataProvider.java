package dataProviders;

import java.util.List;

public interface IDataProvider {
        Book getById(long id);
        List<Book> selectBooks();
        void insert(Book book);
        void delete(long id);
        void update(Book book);
    }
