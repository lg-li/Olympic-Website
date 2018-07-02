package cn.edu.neu.assignment.inter;


import cn.edu.neu.assignment.model.IndividualCompetition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface IndividualCompetitionRepository extends JpaRepository<IndividualCompetition,Integer> {
    List<IndividualCompetition> findAllByIndividual_Id(Integer id);

    @Transactional
    void deleteAllByCompetition_Id(Integer id);

    List<IndividualCompetition> findAllByCompetition_Id(Integer id);
}
