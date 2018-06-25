package com.neu.demo.model.old;

import javax.persistence.*;

@Entity
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(nullable = false)
    private BookEntity bookEntity;

    @ManyToOne()
    @JoinColumn(nullable = false)
    private User fromUser;

    @ManyToOne()
    @JoinColumn(nullable = false)
    private User toUser;

    @Column(nullable = false)
    private String fromUserAddress;
    @Column(nullable = false)
    private String fromUserPhone;
    @Column(nullable = false)
    private String fromUserDate;

    @Column(nullable = false)
    private String toUserAddress;

    @Column(nullable = false)
    private String toUserPhone;

    @Column(nullable = false)
    private String toUserDate;

    @Column(nullable = false)
    private int rentMaxTime;

    @Column(nullable = false,precision = 5, scale = 2)
    private float price;

    public Record() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BookEntity getBookEntity() {
        return bookEntity;
    }

    public void setBookEntity(BookEntity bookEntity) {
        this.bookEntity = bookEntity;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public String getFromUserAddress() {
        return fromUserAddress;
    }

    public void setFromUserAddress(String fromUserAddress) {
        this.fromUserAddress = fromUserAddress;
    }

    public String getFromUserPhone() {
        return fromUserPhone;
    }

    public void setFromUserPhone(String fromUserPhone) {
        this.fromUserPhone = fromUserPhone;
    }

    public String getFromUserDate() {
        return fromUserDate;
    }

    public void setFromUserDate(String fromUserDate) {
        this.fromUserDate = fromUserDate;
    }

    public String getToUserAddress() {
        return toUserAddress;
    }

    public void setToUserAddress(String toUserAddress) {
        this.toUserAddress = toUserAddress;
    }

    public String getToUserPhone() {
        return toUserPhone;
    }

    public void setToUserPhone(String toUserPhone) {
        this.toUserPhone = toUserPhone;
    }

    public String getToUserDate() {
        return toUserDate;
    }

    public void setToUserDate(String toUserDate) {
        this.toUserDate = toUserDate;
    }

    public int getRentMaxTime() {
        return rentMaxTime;
    }

    public void setRentMaxTime(int rentMaxTime) {
        this.rentMaxTime = rentMaxTime;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
