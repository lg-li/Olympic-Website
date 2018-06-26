package cn.edu.neu.assignment.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Sport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 32, nullable = false)
    private String type;

    @OneToMany(mappedBy = "type", fetch = FetchType.EAGER)
    private Set<Competition> competitions = new HashSet<>();

    public Sport() {

    }

    public Set<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(Set<Competition> competitions) {
        this.competitions = competitions;
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
}
