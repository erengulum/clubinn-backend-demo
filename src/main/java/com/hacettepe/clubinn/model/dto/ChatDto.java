package com.hacettepe.clubinn.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Chat Data Transfer Object")
public class ChatDto {

    @ApiModelProperty(value = "Chat SubClub")
    private String subClubName;

    @ApiModelProperty(value = "Chat Description")
    private String chatDescription;

    @ApiModelProperty(value = "Chat Message List")
    private Collection<MessageDto> messageList;

}
