package org.project.stockqueen.repository;

import org.project.stockqueen.entity.DemandForecasting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandJpaRepository extends JpaRepository<DemandForecasting, Long> {

}
