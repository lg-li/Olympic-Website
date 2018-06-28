package cn.edu.neu.assignment.model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "team_competition")
public class TeamCompetition implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JSONField(serialize = false)
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @JSONField(serialize = false)
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @Column(nullable = false)
    private short rank;

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
}
