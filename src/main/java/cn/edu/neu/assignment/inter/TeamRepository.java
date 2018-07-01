package cn.edu.neu.assignment.inter;

import cn.edu.neu.assignment.model.Team;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Integer> {
    @Override
    @EntityGraph(value = "team.findById", type = EntityGraphType.FETCH)
    Optional<Team> findById(Integer integer);

    List<Team> findAllByDelegations_Id(Integer id);
}
