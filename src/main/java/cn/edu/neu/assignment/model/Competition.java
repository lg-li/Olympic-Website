package cn.edu.neu.assignment.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 32,nullable = false)
    private String name;

    @Column(length = 128,nullable = false)
    private String place;

    @Column(nullable = false)
    private Date time;

    @Column(length = 32,nullable = false)
    private String type;

    @OneToMany(mappedBy = "competition",fetch=FetchType.EAGER)
    private Set<IndividualCompetition> individualCompetitions;

    @OneToMany(mappedBy = "competition",fetch=FetchType.EAGER)
    private Set<TeamCompetition> teamCompetitions;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
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
}
