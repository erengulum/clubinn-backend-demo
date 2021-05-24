package com.hacettepe.clubinn.controller;

import com.hacettepe.clubinn.config.helper.ResponseMessage;
import com.hacettepe.clubinn.model.dto.MessageDto;
import com.hacettepe.clubinn.model.dto.MessageHistoryDto;
import com.hacettepe.clubinn.model.dto.TokenResponse;
import com.hacettepe.clubinn.service.ChatService;
import com.hacettepe.clubinn.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/chat")
@Api(value = "Chat API")
@CrossOrigin(origins = ApiPaths.LOCAL_CLIENT_BASE_PATH, maxAge = 3600)
public class ChatController {

    private final ChatService chatService;
    private final ResponseMessage responseMessage;

    public ChatController(ChatService chatService, ResponseMessage responseMessage) {
        this.chatService = chatService;
        this.responseMessage = responseMessage;
    }

    @RequestMapping(value = "/{chatid}", method = RequestMethod.GET)
    @ApiOperation(value = "Join Chat Operation", response = ResponseMessage.class)
    public ResponseEntity<ResponseMessage> joinChat(@PathVariable Long chatid) {
        Boolean response = chatService.join(chatid);
        if (!response) {
            responseMessage.setResponseMessage("User already in a chat !");
            return null;
        }
        return ResponseEntity.ok(responseMessage);
    }

    @RequestMapping(value = "/disconnect/{chatid}", method = RequestMethod.PUT)
    @ApiOperation(value = "Leave Chat Operation", response = String.class)
    public ResponseEntity<String> leaveChat(@PathVariable Long chatid) {
        return ResponseEntity.ok(chatService.disconnect(chatid));
    }

    @RequestMapping(value = "/send/{chatid}", method = RequestMethod.POST)
    @ApiOperation(value = "Send Message Operation", response = MessageDto.class)
    public ResponseEntity<MessageDto> sendMessage(@PathVariable Long chatid, @Validated @RequestBody MessageDto messageDto) {
        return ResponseEntity.ok(chatService.sendMessage(chatid, messageDto));

    }

    @RequestMapping(value = "/sendbysubgroup/{subgroupId}", method = RequestMethod.POST)
    @ApiOperation(value = "Send Message By Group ID Operation", response = MessageDto.class)
    public ResponseEntity<MessageDto> sendBySubGroupId(@PathVariable Long subgroupId, @Validated @RequestBody MessageDto messageDto) {
        return ResponseEntity.ok(chatService.sendMessageBySubclubId(subgroupId, messageDto));

    }

    @RequestMapping(value = "/messages/{chatid}", method = RequestMethod.GET)
    @ApiOperation(value = "Get Message List Operation", response = MessageHistoryDto.class)
    public ResponseEntity<List<MessageHistoryDto>> getMessageList(@PathVariable Long chatid) {

        return ResponseEntity.ok(chatService.getChatMessages(chatid));
    }

    @RequestMapping(value = "subclub/messages/{subclubId}", method = RequestMethod.GET)
    @ApiOperation(value = "Get Message List by Sub Club ID Operation", response = MessageHistoryDto.class)
    public ResponseEntity<List<MessageHistoryDto>> getMessageListBySubclubId(@PathVariable Long subclubId) {

        return ResponseEntity.ok(chatService.getChatMessagesBySubclubId(subclubId));
    }


}