package org.example;

public class ConstantsProperties {
    public static final String MY_EXAMPLE_CONSTANT = "keytoprop";
    public static final String ID = "id";
    public static final String BOOK_NAME = "bookName";
    public static final String COUNTRY = "country";
    public static final String PATH_TO_CSV = "bookInfo.csv";
    public static final String PATH_TO_XML = "bookInfo.xml";
    public static final String BOOK = "book";
    public static final String BOOKS = "books";

    public static final String MONGODB_HOST = "mongodb://localhost:27017";
    public static final String MONGODB_DATABASE_NAME = "DenisDB";
    public static final String MONGODB_COLLECTION_NAME = "DenisDB";
    public static final String MONGODB_DATE_TEMPLATE = "yyyy/MM/dd";
    public static final String MONGODB_COLLECTION_ALREADY_EXIST = "collection already exist";

    public static final String ACTOR_NAME = "system!";

    public static final String MY_SQL_ADDRESS = "jdbc:mysql://localhost:3306/booklib_sql";
    public static final String MY_SQL_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String MY_SQL_USER_NAME = "root";
    public static final String MY_SQL_PASSWORD = "civi~ZNPWXyKLP{";
    public static final String MY_SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + "Books ("
            + "id SERIAL, "
            + "bookName TEXT, "
            + "country TEXT);";
    public static final String MY_SQL_INSERT = "INSERT INTO Books(id, bookName, country) VALUES ('%s', '%s', '%s');";
    public static final String MY_SQL_GET_ID = "SELECT * FROM Books WHERE id = %d;";
    public static final String MY_SQL_SELECT_ALL_DATA = "SELECT * FROM Books;";
    public static final String MY_SQL_DELETE = "DELETE FROM Books WHERE id = %d;";
    public static final String MY_SQL_UPDATE = "UPDATE Books SET bookName = '%s', country = '%s' WHERE id = %d;";
    public static final String MY_SQL_DELETE_ALL_DATA = "DELETE FROM Books";
}
