package cn.edu.neu.assignment.inter;

import cn.edu.neu.assignment.model.Team;
import cn.edu.neu.assignment.model.Team;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Integer> {
    @Override
    @EntityGraph(value = "team.findById", type = EntityGraphType.FETCH)
    Optional<Team> findById(Integer integer);

    List<Team> findAllByDelegations_Id(Integer id);

    @Query(value = "select distinct team from Team team where exists(select competition from Competition competition where exists (select teamCompetition from TeamCompetition teamCompetition where teamCompetition.team.id = team.id and teamCompetition.competition.id = competition.id) and competition.type.id = ?1) ")
    List<Team> findAllBySportId(Integer sportId);

    @Query(value = "select distinct team from Team team where team.delegations.id =?2 and exists(select competition from Competition competition where exists (select teamCompetition from TeamCompetition teamCompetition where teamCompetition.team.id = team.id and teamCompetition.competition.id = competition.id) and competition.type.id = ?1) ")
    List<Team> findAllBySportIdAndDelegationsId(Integer sportId, Integer delegations_id);

    List<Team> findAllByNameContains(String name);

    List<Team> findAllByDelegationsIdAndNameContains(Integer delegationsId, String name);

    @Query(value = "select distinct team from Team team where team.name like %?2% and exists(select competition from Competition competition where exists (select teamCompetition from TeamCompetition teamCompetition where teamCompetition.team.id = team.id and teamCompetition.competition.id = competition.id) and competition.type.id = ?1) ")
    List<Team> findAllBySportIdAndNameContains(Integer sportId, String name);

    @Query(value = "select distinct team from Team team where team.delegations.id = ?3 and team.name like %?2% and exists(select competition from Competition competition where exists (select teamCompetition from TeamCompetition teamCompetition where teamCompetition.team.id = team.id and teamCompetition.competition.id = competition.id) and competition.type.id = ?1) ")
    List<Team> findAllByDelegationsIdAndNameContainsAndSportID(Integer sportId, String name, Integer delegationId);

}
