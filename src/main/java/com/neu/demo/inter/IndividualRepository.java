package com.neu.demo.inter;

import com.neu.demo.model.Individual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndividualRepository extends JpaRepository<Individual, Integer> {
}
