package com.pfe.microserviceusers.repository;

import com.pfe.microserviceusers.models.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager,Long> {
}
