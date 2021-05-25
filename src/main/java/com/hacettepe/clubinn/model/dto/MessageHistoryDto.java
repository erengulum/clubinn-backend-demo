package com.hacettepe.clubinn.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Message History Data Transfer Object")
public class MessageHistoryDto {

    @ApiModelProperty(value = "Message History Date")
    private String date;

    @ApiModelProperty(value = "Message History Content")
    private String content;

    @ApiModelProperty(value = "Message History User")
    private UserDto user;
}

