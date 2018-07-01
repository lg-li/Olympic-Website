package cn.edu.neu.assignment.inter;

import cn.edu.neu.assignment.model.Delegation;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DelegationRepository extends JpaRepository<Delegation, Integer> {
    @Query("select d from Delegation d where d.name=?1")
    Delegation findByName(String name);

    @EntityGraph(value="delegation.all",type=EntityGraphType.FETCH)
    List findAll();

    @EntityGraph(value="delegation.all",type=EntityGraphType.FETCH)
    Optional<Delegation> findById(Integer integer);
}
