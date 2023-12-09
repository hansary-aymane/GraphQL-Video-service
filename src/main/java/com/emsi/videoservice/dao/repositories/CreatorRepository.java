package com.emsi.videoservice.repositories;

import com.emsi.videoservice.dao.entities.Creator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatorRepository extends JpaRepository<Creator, Long> {
}
