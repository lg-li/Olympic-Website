package com.neu.demo.inter;

import com.neu.demo.model.old.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
