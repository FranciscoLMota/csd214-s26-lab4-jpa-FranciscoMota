package bookstore.pojos;

import java.util.Objects;
import java.util.Scanner;
import bookstore.entities.BookEntity;

public class Book extends Publication {
    private String author = "";
    private String isbn = "";

    public Book() {
        super();
    }

    public Book(String author) {
        this.author = author;
    }

    public Book(String author, String title, double price, int copies, String isbn) {
        super(title, price, copies);
        this.author = author;
        this.isbn = isbn;
    }

    @Override
    public void initialize(Scanner input) {
        // Pass scanner up to parent
        super.initialize(input);

        System.out.println("Enter Author:");
        this.author = getInput(input, "Unknown Author");

        System.out.println("Enter ISBN:");
        this.isbn = getInput(input, "Unknown ISBN");

        // Pass scanner to helper
        super.initPriceCopies(input);
    }

    @Override
    public void edit(Scanner input) {
        super.edit(input);

        System.out.println("Edit Author [" + this.author + "]:");
        this.author = getInput(input, this.author);

        System.out.println("Edit ISBN [" + this.isbn + "]:");
        this.isbn = getInput(input, this.isbn);
    }


    @Override
    public void sellItem() {
        System.out.println("Selling Book: " + getTitle() + " by " + author);
        setCopies(getCopies() - 1);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Book{author='" + author + "', " + super.toString() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), author);
    }

    // Mapping: DTO to Database Entity
    public BookEntity toEntity() {
        BookEntity entity = new BookEntity();
        entity.setId(this.getDbId());
        entity.setProductId(this.getProductId());
        entity.setTitle(this.getTitle());
        entity.setPrice(this.getPrice());
        entity.setCopies(this.getCopies());
        entity.setAuthor(this.getAuthor());
        entity.setIsbn(this.getIsbn());
        return entity;
    }

    // Mapping: Database Entity to DTO
    public static Book fromEntity(BookEntity entity) {
        Book book = new Book(
                entity.getAuthor(),
                entity.getTitle(),
                entity.getPrice(),
                entity.getCopies(),
                entity.getIsbn()
        );
        book.setDbId(entity.getId());
        book.setProductId(entity.getProductId());
        return book;
    }
}
