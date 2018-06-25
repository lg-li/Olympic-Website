package cn.edu.neu.assignment.inter;

import cn.edu.neu.assignment.model.Delegation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DelegationRepository extends JpaRepository<Delegation, Integer> {
}
