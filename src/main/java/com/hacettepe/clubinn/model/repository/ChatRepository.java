package com.hacettepe.clubinn.model.repository;

import com.hacettepe.clubinn.model.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat,Long> {

    Chat findBySubClub_Id(Long id);

    Chat findBySubClub_SubClubName(String subClubName);

}
