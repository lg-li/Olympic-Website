package cn.edu.neu.assignment.model;

import cn.edu.neu.assignment.inter.IndividualCompetitionRepository;
import cn.edu.neu.assignment.inter.IndividualRepository;
import cn.edu.neu.assignment.inter.TeamCompetitionRepository;
import cn.edu.neu.assignment.inter.TeamRepository;
import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
@NamedEntityGraphs({@NamedEntityGraph(name = "delegation.all", attributeNodes = {
        @NamedAttributeNode(value = "individuals", subgraph = "individualCompetitions"), @NamedAttributeNode(value = "teams", subgraph = "teamCompetitions"),
}, subgraphs = {
        @NamedSubgraph(name = "individualCompetitions", attributeNodes = @NamedAttributeNode("individualCompetitions")), @NamedSubgraph(name = "teamCompetitions", attributeNodes = @NamedAttributeNode("teamCompetitions"))
}
)})
public class Delegation implements Comparable<Delegation> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JSONField(serialize = false)
    private String description;

    @Column(length = 32, nullable = false)
    private String name;

    @OneToMany(mappedBy = "delegations", fetch = FetchType.LAZY)
    @JSONField(serialize = false)
    private Set<Individual> individuals = new HashSet<>();

    @OneToMany(mappedBy = "delegations", fetch = FetchType.LAZY)
    @JSONField(serialize = false)
    private Set<Team> teams = new HashSet<>();

    //0 Africa; 1 America; 2 Asia Pacific; 3 Europe; 4 Oceania
    @Column(nullable = false)
    private short continent;

    @Transient
    private int gold;
    @Transient
    private int silver;
    @Transient
    private int bronze;

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

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getSilver() {
        return silver;
    }

    public void setSilver(int silver) {
        this.silver = silver;
    }

    public int getBronze() {
        return bronze;
    }

    public void setBronze(int bronze) {
        this.bronze = bronze;
    }


    public void countMedals() {
        //Variables of individual
        Iterator<Individual> individualIterator = individuals.iterator();
        Iterator<IndividualCompetition> individualCompetitionIterator;
        Individual individual;
        IndividualCompetition individualCompetition;

        //Variables of team
        Iterator<Team> teamIterator = teams.iterator();
        Iterator<TeamCompetition> teamCompetitionIterator;
        Team team;
        TeamCompetition teamCompetition;

        gold = 0;
        silver = 0;
        bronze = 0;
        while (individualIterator.hasNext()) {
            individual = individualIterator.next();
            individualCompetitionIterator = individual.getIndividualCompetitions().iterator();
            while (individualCompetitionIterator.hasNext()) {
                individualCompetition = individualCompetitionIterator.next();
                if (individualCompetition.getRank() == 1)
                    gold++;
                if (individualCompetition.getRank() == 2)
                    silver++;
                if (individualCompetition.getRank() == 3)
                    bronze++;
            }
        }

        while (teamIterator.hasNext()) {
            team = teamIterator.next();
            teamCompetitionIterator = team.getTeamCompetitions().iterator();
            while (teamCompetitionIterator.hasNext()) {
                teamCompetition = teamCompetitionIterator.next();
                if (teamCompetition.getRank() == 1)
                    gold++;
                if (teamCompetition.getRank() == 2)
                    silver++;
                if (teamCompetition.getRank() == 3)
                    bronze++;
            }
        }
    }

    @Override
    public int compareTo(Delegation that) {
        this.countMedals();
        that.countMedals();
        int x = (this.getGold() + this.getSilver() + this.getBronze()) - (that.getGold() + that.getSilver() + that.getBronze());
        int y = this.getGold() - that.getGold();
        int z = this.getSilver() - that.getSilver();
        if (x == 0) {
            if (y == 0) {
                return z;
            }
            return y;
        }
        return x;
    }
}
