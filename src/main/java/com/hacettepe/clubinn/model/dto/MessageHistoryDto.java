package com.hacettepe.clubinn.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hacettepe.clubinn.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageHistoryDto {

    private String date;

    private String content;

    private UserDto user;
}
