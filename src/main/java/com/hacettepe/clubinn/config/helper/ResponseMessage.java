package com.hacettepe.clubinn.config.helper;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ResponseMessage {

    private int responseType; // 1=> No Problem.Registration is done 0=>There is a problem but it's not user's fault
    private String responseMessage;

}
