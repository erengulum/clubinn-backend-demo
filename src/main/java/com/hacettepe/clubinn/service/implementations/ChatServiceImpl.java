package com.hacettepe.clubinn.service.implementations;

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

    /**
     * Chat creation while creating new Sub Club
     *
     * @param subClub --> Sub Club that chat will create
     * @return --> Returns Sub Club Chat
     **/
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

    /**
     * Deletes the subclub chat in the admin panel
     *
     * @param subClub --> Sub Club that chat will delete
     **/
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

    /**
     * Gets the user into the chat.
     *
     * @param id --> User id
     * @return --> Whether s/he could attend the chat
     **/
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

    /**
     * User disconnect from chat.
     *
     * @param id --> User id
     * @return --> Whether s/he could Leave the chat
     **/
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

    /**
     * User send message to chat.
     *
     * @param id         --> Chat id
     * @param messageDto --> MessageDto from user
     * @return --> MessageDto to Chat
     **/
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

    /**
     * User send message to chat by sub club id.
     *
     * @param subclubId      --> User id
     * @param messageDto     --> MessageDto from user
     * @param senderUsername --> Sender User
     * @return --> MessageDto to Chat
     **/
    @Override
    public MessageHistoryDto sendMessageBySubclubId(Long subclubId, String senderUsername, MessageDto messageDto) {

        if (chatRepository.getBySubClubId(subclubId) == null) {
            log.warn("bu subgrup icin chat henüz olusturulmadi");
            return null;
        }
        User sender = userRepository.findByUsername(senderUsername);

        if (sender == null) {
            return null;
        }
        Long chatId = chatRepository.getBySubClubId(subclubId).getId();

        String date = formatter.format(new Date());

        Chat chat = chatRepository.getOne(chatId);

        Message newMessage = new Message();
        newMessage.setContent(slangWordDetector(messageDto.getContent()));
        newMessage.setDate(date);
        newMessage.setChat(chat);

        newMessage.setUser(sender);
        messageRepository.save(newMessage);

        chat.getMessageList().add(newMessage);
        chatRepository.save(chat);
        return modelMapper.map(newMessage, MessageHistoryDto.class);
    }

    /**
     * Slang word detection mechanism for chat.
     *
     * @param message --> User message
     * @return --> MessageDto to Chat
     **/
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

    /**
     * Get all chat messages.
     *
     * @param chatId --> Chat ID
     * @return --> MessageDto to Chat
     **/
    @Override
    public List<MessageHistoryDto> getChatMessages(Long chatId) {

        List<Message> messages = messageRepository.getAllByChat_Id(chatId);

        return Arrays.asList(modelMapper.map(messages, MessageHistoryDto[].class));
    }


    /**
     * Get all chat messages by sub club id.
     *
     * @param subclubId --> Sub club ID
     * @return --> Message History of Chat
     **/
    @Override
    public List<MessageHistoryDto> getChatMessagesBySubclubId(Long subclubId) {
        log.warn("mesaj servisine girdi");
        if (chatRepository.getBySubClubId(subclubId) == null) {
            return null;
        }
        Long chatId = chatRepository.getBySubClubId(subclubId).getId();

        List<Message> messages = messageRepository.getAllByChat_Id(chatId);

        ArrayList<MessageHistoryDto> messageHistoryDtos = new ArrayList<MessageHistoryDto>(messages.size());

        for (Message message : messages) {
            MessageHistoryDto messageHistoryDto = new MessageHistoryDto();
            messageHistoryDto.setContent(message.getContent());
            messageHistoryDto.setDate(message.getDate());
            messageHistoryDto.setUser(modelMapper.map(message.getUser(), UserDto.class));

            messageHistoryDtos.add(messageHistoryDto);
        }

        if (messageHistoryDtos.get(0) != null) {
            System.out.println("msj:" + messages.get(0).getUser().getUsername());

        } else {
            System.out.println("msj:hata var");
        }

        return messageHistoryDtos;
    }


    /**
     * This function retrieves the current authenticated user information.
     *
     * @return --> Current authenticated user
     **/
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
