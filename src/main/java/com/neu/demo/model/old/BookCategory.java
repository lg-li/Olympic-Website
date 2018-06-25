package com.neu.demo.model.old;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class BookCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 24,unique = true,nullable = false)
    private String name;

    @Column(nullable = false)
    private int type;

    @ManyToMany(fetch=FetchType.LAZY,mappedBy="bookCategory")
    private Set<Book> book = new HashSet<>();

    public BookCategory() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Book> getBook() {
        return book;
    }

    public void setBook(Set<Book> book) {
        this.book = book;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
