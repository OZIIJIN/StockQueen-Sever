package org.project.stockqueen.repository;

import java.util.Optional;
import org.project.stockqueen.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuJpaRepository extends JpaRepository<Menu, Long> {

  Optional<Menu> findByMenuName(String menuName);
}
