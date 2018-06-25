package cn.edu.neu.assignment.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "team_competition")
public class TeamCompetition implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @Id
    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @Column(nullable = false)
    private short rank;

    public TeamCompetition() {
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
