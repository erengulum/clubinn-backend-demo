package com.hacettepe.clubinn.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatDto {

    private String subClubName;

    private String chatDescription;

    private Collection<MessageDto> messageList;

}
