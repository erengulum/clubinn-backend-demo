package com.hacettepe.clubinn.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Feedback Data Transfer Object")
public class FeedbackDto {

    @ApiModelProperty(value = "Feedback ID")
    private Long id;

    @ApiModelProperty(value = "Feedback Owner")
    private UserDto owner;

    @ApiModelProperty(value = "Feedback Type")
    private String feedbackType;

    @ApiModelProperty(value = "Feedback Comment")
    private String comment;

}
