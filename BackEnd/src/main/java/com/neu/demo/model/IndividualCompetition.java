package com.neu.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "individual_competition")
public class IndividualCompetition implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "individual_id")
    private Individual individual;

    @Id
    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @Column(nullable = false)
    private short rank;

    public IndividualCompetition() {
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
}