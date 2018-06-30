package cn.edu.neu.assignment.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 32, nullable = false)
    private String name;

    @Column(length = 128, nullable = false)
    private String place;

    @Column(nullable = false)
    @JSONField(format="yyyy-MM-dd HH:mm")
    private Date time;

    @ManyToOne()
    private Sport type;

    @OneToMany(mappedBy = "competition")
    @JSONField(serialize = false)
    private Set<IndividualCompetition> individualCompetitions;

    @OneToMany(mappedBy = "competition")
    @JSONField(serialize = false)
    private Set<TeamCompetition> teamCompetitions;

    private short situation;

    @Column(nullable = false)
    private boolean individual;

    public Competition() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Sport getType() {
        return type;
    }

    public void setType(Sport type) {
        this.type = type;
    }

    public Set<IndividualCompetition> getIndividualCompetitions() {
        return individualCompetitions;
    }

    public void setIndividualCompetitions(Set<IndividualCompetition> individualCompetitions) {
        this.individualCompetitions = individualCompetitions;
    }

    public Set<TeamCompetition> getTeamCompetitions() {
        return teamCompetitions;
    }

    public void setTeamCompetitions(Set<TeamCompetition> teamCompetitions) {
        this.teamCompetitions = teamCompetitions;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public short getSituation() {
        return situation;
    }

    public void setSituation(short situation) {
        this.situation = situation;
    }

    public boolean isIndividual() {
        return individual;
    }

    public void setIndividual(boolean individual) {
        this.individual = individual;
    }
}
