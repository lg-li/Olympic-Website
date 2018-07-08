package cn.edu.neu.assignment.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The entity to keep information of the relation between a individual athlete and a competition
 */
@Entity
@Table(name = "individual_competition")
public class IndividualCompetition implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Mapped to the individual of this record
     */
    @ManyToOne
    @JoinColumn(name = "individual_id")
    private Individual individual;

    /**
     * Mapped to the competition of this record
     */
    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @Column(nullable = true)
    private short rank;

    @Column(nullable = true)
    private String result;

    public IndividualCompetition() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Individual getIndividual() {
        return individual;
    }

    public void setIndividual(Individual individual) {
        this.individual = individual;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public short getRank() {
        return rank;
    }

    public void setRank(short rank) {
        this.rank = rank;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}