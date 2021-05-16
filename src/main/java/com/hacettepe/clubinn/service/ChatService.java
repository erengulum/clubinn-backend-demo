package com.hacettepe.clubinn.service;

import com.hacettepe.clubinn.model.dto.ChatDto;
import com.hacettepe.clubinn.model.dto.MessageDto;
import com.hacettepe.clubinn.model.entity.Chat;
import com.hacettepe.clubinn.model.entity.SubClub;

public interface ChatService {

    Chat createChat(SubClub subClub);

    void deleteChat(SubClub subClub);

    ChatDto join(Long id);

    String disconnect(Long id);

    MessageDto sendMessage(Long id, MessageDto messageDto);

    String slangWordDetector(String message);

}
