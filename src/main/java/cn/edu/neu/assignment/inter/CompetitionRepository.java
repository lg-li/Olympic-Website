package cn.edu.neu.assignment.inter;

import cn.edu.neu.assignment.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition, Integer> {
}
