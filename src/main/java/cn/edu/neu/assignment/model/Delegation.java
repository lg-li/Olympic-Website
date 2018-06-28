package cn.edu.neu.assignment.model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
public class Delegation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    @Column(length = 32, nullable = false)
    private String name;

    @OneToMany(mappedBy = "delegations",fetch = FetchType.EAGER)
    private Set<Individual> individuals = new HashSet<>();

    @OneToMany(mappedBy = "delegations",fetch = FetchType.EAGER)
    private Set<Team> teams = new HashSet<>();

    //0 Africa; 1 America; 2 Asia Pacific; 3 Europe; 4 Oceania
    @Column(nullable = false)
    private short continent;

    public Delegation() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Individual> getIndividuals() {
        return individuals;
    }

    public void setIndividuals(Set<Individual> individuals) {
        this.individuals = individuals;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public short getContinent() {
        return continent;
    }

    public void setContinent(short continent) {
        this.continent = continent;
    }

    public int countMedals(int kind){
        Iterator<Individual> individualIterator = individuals.iterator();
        Iterator<IndividualCompetition> individualCompetitionIterator;
        Iterator<Team> teamIterator = teams.iterator();
        Iterator<TeamCompetition> teamCompetitionIterator;
        Individual individual;
        Team team;
        int amount = 0;
        while (individualIterator.hasNext()){
            individual = individualIterator.next();
            individualCompetitionIterator = individual.getIndividualCompetitions().iterator();
            while (individualCompetitionIterator.hasNext()){
                if (individualCompetitionIterator.next().getRank()==kind)
                    amount++;
            }
        }
        while (teamIterator.hasNext()){
            team = teamIterator.next();
            teamCompetitionIterator = team.getTeamCompetitions().iterator();
            while(teamCompetitionIterator.hasNext()){
                if (teamCompetitionIterator.next().getRank()==kind)
                    amount++;
            }
        }
        return amount;
    }

}
