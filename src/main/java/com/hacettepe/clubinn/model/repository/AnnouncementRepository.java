package com.hacettepe.clubinn.model.repository;

import com.hacettepe.clubinn.model.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

    List<Announcement> findAllBySubClub_Id(Long id);

}
