package cn.edu.neu.assignment.inter;

import cn.edu.neu.assignment.model.Individual;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IndividualRepository extends JpaRepository<Individual, Integer> {
    @Override
    @EntityGraph(value = "individual.findById", type = EntityGraphType.FETCH)
    Optional<Individual> findById(Integer integer);

    List<Individual> findAllByDelegations_Id(Integer id);
}
