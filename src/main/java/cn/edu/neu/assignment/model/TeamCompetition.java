package cn.edu.neu.assignment.model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The entity to keep information of the relation between team and competition
 */
@Entity
@Table(name = "team_competition")
public class TeamCompetition implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Mapped to the team of this record
     */
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    /**
     * Mapped to he competition of this record
     */
    @ManyToOne()
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @Column(nullable = true)
    private short rank;

    private String result;

    public TeamCompetition() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
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
