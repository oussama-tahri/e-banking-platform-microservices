package com.adria.eerservice.repositories;

import com.adria.eerservice.entities.EER;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EERRepository extends JpaRepository<EER,Long> {
    List<EER> findByUserId(Long userId);
}
