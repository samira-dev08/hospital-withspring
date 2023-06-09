package com.company.repository;

import com.company.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position,Long> {
    Position findPositionByIdAndActive(Long positionId, Integer Active);
}
