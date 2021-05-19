package com.hacettepe.clubinn.model.repository;

import com.hacettepe.clubinn.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {

    List<Message> getAllByChat_Id(Long id);


}
