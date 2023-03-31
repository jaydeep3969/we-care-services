package com.wecare.coachservice.repository;

import com.wecare.coachservice.entity.CoachEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<CoachEntity, String> {
}
