package dataProviders.mySql;
import dataProviders.DataProviderTools;
import dataProviders.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.AbstractDataProvider;
import org.example.ConstantsProperties;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DataProvider extends AbstractDataProvider {

    private static final Logger log = LogManager.getLogger(DataProvider.class);
    private static Statement statement;
    private static ResultSet resultSet;
    static Connection connection;

    public static Statement prepareDataBase() {
        try {
            Class.forName(ConstantsProperties.MY_SQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(ConstantsProperties.MY_SQL_ADDRESS,
                    ConstantsProperties.MY_SQL_USER_NAME, ConstantsProperties.MY_SQL_PASSWORD);
            statement = connection.createStatement();
            statement.execute(ConstantsProperties.MY_SQL_CREATE_TABLE);
        } catch (SQLException | ClassNotFoundException e) {
            log.error(e);
            closeConnection(connection);
        }
        return statement;
    }

    public static void dropData(){
        statement = prepareDataBase();
        try {
            statement.execute(ConstantsProperties.MY_SQL_DELETE_ALL_DATA);
        } catch (SQLException e) {
            log.error(e);
            closeConnection(connection);
        }
        closeConnection(connection);
    }
    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error(e);
        }
    }
    @Override
    public Book getById(long id) {
        Book book = new Book();
        statement = prepareDataBase();
        try {
            resultSet = statement.executeQuery(String.format(ConstantsProperties.MY_SQL_GET_ID, id));
        } catch (SQLException e) {
            log.error(e);
            closeConnection(connection);
        }
        try {
            if (resultSet.next()) {
                book.setId(resultSet.getLong(1));
                book.setBookName(resultSet.getString(2));
                book.setCountry(resultSet.getString(3));
            }
        } catch (SQLException e) {
            log.error(e);
            closeConnection(connection);
        }
        closeConnection(connection);
        return book;
    }

    @Override
    public List<Book> selectBooks() {
        boolean a = true;
        if(a);
        List<Book> books = new ArrayList<>();
        statement = prepareDataBase();
        try {
            resultSet = statement.executeQuery(ConstantsProperties.MY_SQL_SELECT_ALL_DATA);
        } catch (SQLException e) {
            log.error(e);
            closeConnection(connection);
        }
        try {
            while (resultSet.next()) {
                Book book = new Book(resultSet.getLong(1), resultSet.getString(2),
                        resultSet.getString(3));
                books.add(book);
            }
        } catch (SQLException e) {
            log.error(e);
            closeConnection(connection);
        }
        closeConnection(connection);
        return books;
    }

    @Override
    public void insert(Book book) {
        statement = prepareDataBase();
        try {
            statement.execute(String.format(ConstantsProperties.MY_SQL_INSERT,
                    book.getId(), book.getBookName(), book.getCountry()));
        } catch (SQLException e) {
            log.error(e);
            closeConnection(connection);
        }
        closeConnection(connection);
    }

    @Override
    public void delete(long id) {
        statement = prepareDataBase();
        try {
            statement.execute(String.format(ConstantsProperties.MY_SQL_DELETE, id));
        } catch (SQLException e) {
            log.error(e);
            closeConnection(connection);
        }
        closeConnection(connection);
    }

    @Override
    public void update(Book book) {
        statement = prepareDataBase();
        try {
            statement.execute(String.format(ConstantsProperties.MY_SQL_UPDATE,
                    book.getBookName(), book.getCountry(), book.getId()));
        } catch (SQLException e) {
            log.error(e);
            closeConnection(connection);
        }
        closeConnection(connection);
    }

}
