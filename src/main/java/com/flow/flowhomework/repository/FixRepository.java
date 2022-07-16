package com.flow.flowhomework.repository;

import com.flow.flowhomework.model.Fix;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FixRepository extends JpaRepository<Fix, Long> {

    boolean existsByFixExtension(String extension);
}
