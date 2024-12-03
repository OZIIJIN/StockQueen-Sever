package org.project.stockqueen.repository;

import org.project.stockqueen.entity.DemandForecasting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandJpaRepository extends JpaRepository<DemandForecasting, Long> {

}
