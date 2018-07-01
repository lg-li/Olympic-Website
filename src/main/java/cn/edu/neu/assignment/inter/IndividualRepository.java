package cn.edu.neu.assignment.inter;

import cn.edu.neu.assignment.model.Individual;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IndividualRepository extends JpaRepository<Individual, Integer> {
    @Override
    @EntityGraph(value = "individual.findById", type = EntityGraphType.FETCH)
    Optional<Individual> findById(Integer integer);

    List<Individual> findAllByDelegations_Id(Integer id);

    @Query(value = "select distinct individual from Individual individual where exists(select competition from Competition competition where exists (select individualCompetition from IndividualCompetition individualCompetition where individualCompetition.individual.id = individual.id and individualCompetition.competition.id = competition.id) and competition.type.id = ?1) ")
    List<Individual> findAllBySportId(Integer sportId);

    @Query(value = "select distinct individual from Individual individual where individual.delegations.id =?2 and exists(select competition from Competition competition where exists (select individualCompetition from IndividualCompetition individualCompetition where individualCompetition.individual.id = individual.id and individualCompetition.competition.id = competition.id) and competition.type.id = ?1) ")
    List<Individual> findAllBySportIdAndDelegationsId(Integer sportId, Integer delegations_id);

    List<Individual> findAllByNameContains(String name);

    List<Individual> findAllByDelegationsIdAndNameContains(Integer delegationsId, String name);

    @Query(value = "select distinct individual from Individual individual where individual.name like %?2% and exists(select competition from Competition competition where exists (select individualCompetition from IndividualCompetition individualCompetition where individualCompetition.individual.id = individual.id and individualCompetition.competition.id = competition.id) and competition.type.id = ?1) ")
    List<Individual> findAllBySportIdAndNameContains(Integer sportId, String name);

    @Query(value = "select distinct individual from Individual individual where individual.delegations.id = ?3 and individual.name like %?2% and exists(select competition from Competition competition where exists (select individualCompetition from IndividualCompetition individualCompetition where individualCompetition.individual.id = individual.id and individualCompetition.competition.id = competition.id) and competition.type.id = ?1) ")
    List<Individual> findAllByDelegationsIdAndNameContainsAndSportID(Integer sportId, String name, Integer delegationId);

}
