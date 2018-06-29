package cn.edu.neu.assignment.model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 64,nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private char sex;

    @OneToMany(mappedBy = "team",fetch=FetchType.EAGER)
    private Set<TeamCompetition> teamCompetitions = new HashSet<>();

    @ManyToOne
    @JSONField(serialize = false)
    @JoinColumn(name = "delegation_id")
    private Delegation delegations;

    @ManyToMany(mappedBy = "teams",fetch=FetchType.EAGER)
    @JSONField(serialize = false)
    private Set<Individual> individuals = new HashSet<>();

    public Team(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

}
