package cn.edu.neu.assignment.inter;

import cn.edu.neu.assignment.model.Individual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndividualRepository extends JpaRepository<Individual, Integer> {
}
