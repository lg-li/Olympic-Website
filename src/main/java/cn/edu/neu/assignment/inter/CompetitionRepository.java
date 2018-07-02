package cn.edu.neu.assignment.inter;

import cn.edu.neu.assignment.model.Competition;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompetitionRepository extends JpaRepository<Competition, Integer> {
    @EntityGraph(value="competition.findById",type=EntityGraphType.FETCH)
    Optional<Competition> findById(Integer integer);

    List<Competition> findAllByType_Id(Integer typeId);
}
