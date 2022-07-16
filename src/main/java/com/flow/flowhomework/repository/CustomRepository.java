package com.flow.flowhomework.repository;

import com.flow.flowhomework.model.Custom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomRepository extends JpaRepository<Custom, Long> {

    boolean existsByCustomExtension(String customExtension);
}
