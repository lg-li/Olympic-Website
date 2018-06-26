package cn.edu.neu.assignment.inter;

import cn.edu.neu.assignment.model.Sport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Sport,Integer> {
}
