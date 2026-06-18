package bookstore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class BookEntity extends PublicationEntity {
    @Column(name = "author")
    private String author;

    @Column(name = "isbn")
    private String isbn;

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BookEntity() {
    }

    public BookEntity(String title, double price, int copies, String author, String isbn) {
        super(title, price, copies);
        this.author = author;
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "author='" + author + '\'' +
                "isbn='" + isbn + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return Objects.equals(author, that.author) && Objects.equals(isbn, that.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, isbn);
    }
}