package com.neu.demo.model.old;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    private Book book;

    @ManyToOne()
    @JoinColumn(name = "ownerId",nullable = false)
    private User owner;

    @Column(precision = 5, scale = 2,nullable = false)
    private float rentPrice;

    @ManyToOne()
    @JoinColumn(name = "renTypeId",nullable = false)
    private RentType rentType;

    private String note;

    @Column(nullable = false)
    private boolean isRent;

    @OneToMany(mappedBy = "bookEntity")
    private Set<Record> record = new HashSet<>();


    @ManyToMany(mappedBy = "cartBookEntity")
    private Set<User> cartUser = new HashSet<>();

    public BookEntity() {
    }

    public Set<User> getCartUser() {
        return cartUser;
    }

    public void setCartUser(Set<User> cartUser) {
        this.cartUser = cartUser;
    }

    public Set<Record> getRecord() {
        return record;
    }

    public void setRecord(Set<Record> record) {
        this.record = record;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public float getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(float rentPrice) {
        this.rentPrice = rentPrice;
    }

    public RentType getRentType() {
        return rentType;
    }

    public void setRentType(RentType rentType) {
        this.rentType = rentType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isRent() {
        return isRent;
    }

    public void setRent(boolean rent) {
        isRent = rent;
    }
}
