package com.hacettepe.clubinn.service.implementations;
// disconnect stringten çek

import com.hacettepe.clubinn.model.dto.ChatDto;
import com.hacettepe.clubinn.model.dto.MessageDto;
import com.hacettepe.clubinn.model.dto.MessageHistoryDto;
import com.hacettepe.clubinn.model.dto.UserDto;
import com.hacettepe.clubinn.model.entity.Chat;
import com.hacettepe.clubinn.model.entity.Message;
import com.hacettepe.clubinn.model.entity.SubClub;
import com.hacettepe.clubinn.model.entity.User;
import com.hacettepe.clubinn.model.repository.ChatRepository;
import com.hacettepe.clubinn.model.repository.MessageRepository;
import com.hacettepe.clubinn.model.repository.UserRepository;
import com.hacettepe.clubinn.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ChatServiceImpl implements ChatService {

    private final SimpleDateFormat formatter;
    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public ChatServiceImpl(ChatRepository chatRepository, MessageRepository messageRepository, UserRepository userRepository, ModelMapper modelMapper) {
        formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Chat createChat(SubClub subClub) {

        String date = formatter.format(new Date());

        Chat chat = new Chat();
        chat.setChatDescription(subClub.getDescription() + "Chat");
        chat.setSubClub(subClub);
        chat.setCreationDate(date);
        chat.setMessageList(new ArrayList<>());
        chatRepository.save(chat);

        return chat;
    }

    @Override
    public void deleteChat(SubClub subClub) {
        Chat chat = chatRepository.getOne(subClub.getChat().getId());

        for (User user : chat.getUserList()) {
            user.setChat(null);
        }

        if (chat != null) {
            chatRepository.delete(chat);
        }
    }

    @Override
    public Boolean join(Long id) {
        Chat chat = chatRepository.getOne(id);

        User user = getAuthenticatedUser();
        if (user.getChat() != null) {
            log.error("User already in a chat !");
            return Boolean.FALSE;
        }
        user.getChat().add(chat);
        userRepository.save(user);
        chat.getUserList().add(user);
        chatRepository.save(chat);

        if (chat == null) {
            return Boolean.FALSE;
        }

        ChatDto chatDto = modelMapper.map(chat, ChatDto.class);
        List<Message> messageList = messageRepository.getAllByChat_Id(id);
        chatDto.setMessageList(Arrays.asList(modelMapper.map(messageList, MessageDto[].class)));
        return Boolean.TRUE;

    }

    @Override
    public String disconnect(Long id) {
        Chat chat = chatRepository.getOne(id);

        User user = getAuthenticatedUser();
        user.setChat(null);
        userRepository.save(user);
        chat.getUserList().remove(user);
        chatRepository.save(chat);
        return "Successfully Disconnected";
    }

    @Override
    public MessageDto sendMessage(Long id, MessageDto messageDto) {

        String date = formatter.format(new Date());

        Chat chat = chatRepository.getOne(id);

        Message newMessage = new Message();
        newMessage.setContent(slangWordDetector(messageDto.getContent()));
        newMessage.setDate(date);
        newMessage.setChat(chat);
        newMessage.setUser(getAuthenticatedUser());
        messageRepository.save(newMessage);

        chat.getMessageList().add(newMessage);
        chatRepository.save(chat);
        return modelMapper.map(newMessage, MessageDto.class);
    }

    @Override
    public MessageDto sendMessageBySubclubId(Long subclubId, MessageDto messageDto) {

        if(chatRepository.getBySubClubId(subclubId)==null){
            log.warn("bu subgrup icin chat henüz olusturulmadi");
            return null;
        }
        Long chatId = chatRepository.getBySubClubId(subclubId).getId();

        String date = formatter.format(new Date());

        Chat chat = chatRepository.getOne(chatId);

        Message newMessage = new Message();
        newMessage.setContent(slangWordDetector(messageDto.getContent()));
        newMessage.setDate(date);
        newMessage.setChat(chat);
        newMessage.setUser(getAuthenticatedUser());
        messageRepository.save(newMessage);

        chat.getMessageList().add(newMessage);
        chatRepository.save(chat);
        return modelMapper.map(newMessage, MessageDto.class);
    }

    @Override
    public String slangWordDetector(String message) {

        String[] words = message.split("\\s+");
        StringBuilder stringBuilder = new StringBuilder();

        try {
            ClassLoader classLoader = ResourceLoader.class.getClassLoader();
            File file = new File(classLoader.getResource("SlangWords.txt").getFile());
            List<String> badWordList = Files.readAllLines(file.toPath());

            for (String word : words) {
                if (badWordList.contains(word.toLowerCase())) {
                    stringBuilder.append("**** ");
                } else {
                    stringBuilder.append(word).append(" ");
                }
            }
        } catch (Exception e) {
            log.warn("Slang Word Detection error !");
        }
        return stringBuilder.toString();

    }



    @Override
    public List<MessageHistoryDto> getChatMessages(Long chatId) {

        List<Message> messages = messageRepository.getAllByChat_Id(chatId);

         return Arrays.asList(modelMapper.map(messages, MessageHistoryDto[].class));
    }


    @Override
    public List<MessageHistoryDto> getChatMessagesBySubclubId(Long subclubId) {

        if(chatRepository.getBySubClubId(subclubId)==null){
            return null;
        }
        Long chatId = chatRepository.getBySubClubId(subclubId).getId();

        List<Message> messages = messageRepository.getAllByChat_Id(chatId);

        return Arrays.asList(modelMapper.map(messages, MessageHistoryDto[].class));
    }




    private User getAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        return userRepository.findByUsername(username);
    }

}
