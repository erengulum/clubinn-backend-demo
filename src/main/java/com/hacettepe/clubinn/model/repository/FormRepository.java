package com.hacettepe.clubinn.model.repository;

import com.hacettepe.clubinn.model.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepository extends JpaRepository<Form, Long> {

    Form findBySubClub_SubClubName(String subClubName);

    Form findBySubClub_Id(Long subClubId);

}
