package com.hacettepe.clubinn.service;

import com.hacettepe.clubinn.model.dto.ChatDto;
import com.hacettepe.clubinn.model.dto.MessageDto;
import com.hacettepe.clubinn.model.dto.MessageHistoryDto;
import com.hacettepe.clubinn.model.dto.UserDto;
import com.hacettepe.clubinn.model.entity.Chat;
import com.hacettepe.clubinn.model.entity.SubClub;

import java.util.List;

public interface ChatService {

    Chat createChat(SubClub subClub);

    void deleteChat(SubClub subClub);

    Boolean join(Long id);

    String disconnect(Long id);

    MessageDto sendMessage(Long id, MessageDto messageDto);

    MessageDto sendMessageBySubclubId(Long subgroupId, MessageDto messageDto);

    String slangWordDetector(String message);

    List<MessageHistoryDto> getChatMessages(Long chatId);

    List<MessageHistoryDto> getChatMessagesBySubclubId(Long subclubId);

}
