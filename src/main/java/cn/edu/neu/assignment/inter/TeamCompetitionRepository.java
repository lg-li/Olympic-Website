package cn.edu.neu.assignment.inter;

import cn.edu.neu.assignment.model.Competition;
import cn.edu.neu.assignment.model.Team;
import cn.edu.neu.assignment.model.TeamCompetition;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamCompetitionRepository extends JpaRepository<TeamCompetition, Integer> {
    List<TeamCompetition> findAllByTeam_Id(Integer id);

    @Transient
    void deleteAllByCompetition_Id(Integer id);

    List<TeamCompetition> findAllByCompetition_Id(Integer id);
}
