package com.neu.demo.inter;

import com.neu.demo.model.Delegation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DelegationRepository extends JpaRepository<Delegation, Integer> {
}
