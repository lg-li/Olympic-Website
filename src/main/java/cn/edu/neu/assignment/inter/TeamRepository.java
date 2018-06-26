package cn.edu.neu.assignment.inter;

import cn.edu.neu.assignment.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Integer> {
}
