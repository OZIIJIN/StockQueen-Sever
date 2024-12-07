package org.project.stockqueen.repository;

import org.project.stockqueen.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {

  User findByName(String name);

}
