package com.neu.demo.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 32,nullable = false)
    private String type;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "team")
    private Set<TeamCompetition> teamCompetitions = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "delegation_id")
    private Delegation delegations;

    @ManyToMany(mappedBy = "teams")
    private Set<Individual> individuals = new HashSet<>();

    public Team() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<TeamCompetition> getTeamCompetitions() {
        return teamCompetitions;
    }

    public void setTeamCompetitions(Set<TeamCompetition> teamCompetitions) {
        this.teamCompetitions = teamCompetitions;
    }

    public Delegation getDelegations() {
        return delegations;
    }

    public void setDelegations(Delegation delegations) {
        this.delegations = delegations;
    }

    public Set<Individual> getIndividuals() {
        return individuals;
    }

    public void setIndividuals(Set<Individual> individuals) {
        this.individuals = individuals;
    }
}
