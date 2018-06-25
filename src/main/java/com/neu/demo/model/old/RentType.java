package com.neu.demo.model.old;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class RentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 10,nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer rentTime;

    @OneToMany(mappedBy = "rentType")
    private Set<BookEntity> bookEntitie = new HashSet<>();


    public RentType() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<BookEntity> getBookEntitie() {
        return bookEntitie;
    }

    public void setBookEntitie(Set<BookEntity> bookEntitie) {
        this.bookEntitie = bookEntitie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRentTime() {
        return rentTime;
    }

    public void setRentTime(Integer rentTime) {
        this.rentTime = rentTime;
    }
}
