package com.neu.demo.model.old;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
    @Column(length = 28, nullable = false)
    private String id;

    @Column(length = 32, nullable = false)
    private String sessionKey;

    @Column(length = 28, nullable = true)
    private String nickName;

    @Column(length = 255, nullable = true)
    private String avatarUrl;

    @Column(nullable = true,length = 2)
    private Integer gender;//0 female 1 male

    @Column(length = 48, nullable = true)
    private String city;

    @Column(length = 24, nullable = true)
    private String province;

    @Column(length = 24, nullable = true)
    private String country;

    @OneToMany(mappedBy = "owner")
    private Set<BookEntity> bookEntity = new HashSet<>();

    @OneToMany(mappedBy = "fromUser")
    private Set<Record> fromRecord = new HashSet<>();

    @OneToMany(mappedBy = "toUser")
    private Set<Record> toRecord = new HashSet<>();

    @ManyToMany()
    private Set<BookEntity> cartBookEntity = new HashSet<>();

    public User() {

    }

    public Set<BookEntity> getCartBookEntity() {
        return cartBookEntity;
    }

    public void setCartBookEntity(Set<BookEntity> cartBookEntity) {
        this.cartBookEntity = cartBookEntity;
    }

    public Set<Record> getFromRecord() {
        return fromRecord;
    }

    public void setFromRecord(Set<Record> fromRecord) {
        this.fromRecord = fromRecord;
    }

    public Set<Record> getToRecord() {
        return toRecord;
    }

    public void setToRecord(Set<Record> toRecord) {
        this.toRecord = toRecord;
    }

    public User(String id, String sessionKey) {
        this.id = id;
        this.sessionKey = sessionKey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Set<BookEntity> getBookEntity() {
        return bookEntity;
    }

    public void setBookEntity(Set<BookEntity> bookEntitie) {
        this.bookEntity = bookEntitie;
    }
}
