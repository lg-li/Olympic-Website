package cn.edu.neu.assignment.inter;

import cn.edu.neu.assignment.model.Delegation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DelegationRepository extends JpaRepository<Delegation, Integer> {
    @Query("select d from Delegation d where d.name=?1")
    Delegation findByName(String name);
}
