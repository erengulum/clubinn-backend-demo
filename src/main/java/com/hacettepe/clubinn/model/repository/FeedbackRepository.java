package com.hacettepe.clubinn.model.repository;

import com.hacettepe.clubinn.model.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    List<Feedback> getAllByOwnerSubClub_Id(Long subClubId);

}
