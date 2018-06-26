package cn.edu.neu.assignment.inter;

import cn.edu.neu.assignment.model.Competition;
import cn.edu.neu.assignment.model.Team;
import cn.edu.neu.assignment.model.TeamCompetition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamCompetitionRepository extends JpaRepository<TeamCompetition, Integer> {
}
