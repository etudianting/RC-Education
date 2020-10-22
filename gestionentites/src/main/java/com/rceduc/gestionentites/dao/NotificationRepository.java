package com.rceduc.gestionentites.dao;


import com.rceduc.gestionentites.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
public interface NotificationRepository extends JpaRepository<Notification, Long> {



}
