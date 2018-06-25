package com.neu.demo.model.old;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames={"title", "author"})})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true,length = 10)
    private String isbn10;

    @Column(unique = true,length = 13)
    private String isbn13;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false,precision = 5, scale = 2)
    private float price;

    @ManyToMany(fetch=FetchType.LAZY)
    private Set<BookCategory> bookCategory = new HashSet<>();

    @OneToMany(mappedBy = "book")
    private Set<BookEntity> bookEntity = new HashSet<>();

    public Book() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Set<BookCategory> getBookCategory() {
        return bookCategory;
    }


    public void setBookCategory(Set<BookCategory> bookCategory) {
        this.bookCategory = bookCategory;
    }

    public Set<BookEntity> getBookEntity() {
        return bookEntity;
    }

    public void setBookEntity(Set<BookEntity> bookEntity) {
        this.bookEntity = bookEntity;
    }
}
