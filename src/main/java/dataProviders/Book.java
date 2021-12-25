package dataProviders;

import com.opencsv.bean.CsvBindByName;
import org.example.ConstantsProperties;
import java.util.Objects;

public class Book {
    @CsvBindByName(column = ConstantsProperties.ID)
    long id;
    @CsvBindByName(column = ConstantsProperties.BOOK_NAME)
    String bookName;
    @CsvBindByName(column = ConstantsProperties.COUNTRY)
    String country;

    public Book(String bookName, String country) {
        this.id = System.currentTimeMillis();
        this.bookName = bookName;
        this.country = country;
    }

    public Book(long id, String bookName, String country) {
        this.id = id;
        this.bookName = bookName;
        this.country = country;
    }

    public Book() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "MyBook{" +
                "id=" + id +
                ", Book Name = '" + bookName + '\'' +
                ", Country= '" + country + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book mybook = (Book) o;
        return id == mybook.id && Objects.equals(bookName, mybook.bookName) && Objects.equals(country, mybook.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookName, country);
    }
}
