package com.rceduc.gestionentites.dao;


import com.rceduc.gestionentites.entities.Thematique;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface ThematiqueRepository extends JpaRepository<Thematique, Long> {
}
