package cn.edu.neu.assignment.model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The entity to keep information of a team
 */
@Entity
@NamedEntityGraphs({@NamedEntityGraph(name = "team.findById", attributeNodes = {
        @NamedAttributeNode("individuals")
})})
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

    /**
     * Mapped to team competition list of this team
     */
    @OneToMany(mappedBy = "team")
    @JSONField(serialize = false)
    private Set<TeamCompetition> teamCompetitions = new HashSet<>();

    /**
     * Mapped to delegation of this team
     */
    @ManyToOne
    @JSONField(serialize = false)
    @JoinColumn(name = "delegation_id")
    private Delegation delegations;

    /**
     * Mapped to individual list of this team
     */
    @ManyToMany(mappedBy = "teams")
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
