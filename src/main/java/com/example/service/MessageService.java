package com.example.service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
import com.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final AccountService accountService;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountService accountService) {
        this.messageRepository = messageRepository;
        this.accountService = accountService;
    }

    public Message createMessage(Message message) {
  
        if (message.getMessageText() == null || message.getMessageText().isEmpty()) {
            throw new IllegalArgumentException("Message text cannot be blank.");
        }
        if (message.getMessageText().length() > 255) {
            throw new IllegalArgumentException("Message text cannot exceed 255 characters.");
        }
        if (!accountService.findById(message.getPostedBy()).isPresent()) {
            throw new IllegalArgumentException("User does not exist.");
        }
            return messageRepository.save(message);
    }


    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Optional<Message> getMessageById(Integer messageId) {
        return messageRepository.findById(messageId);
    }

    public int deleteMessageById(Integer messageId) {
        if (messageRepository.existsById(messageId)) {
            messageRepository.deleteById(messageId);
            return 1; 
        }
        return 0; 
    }

    public List<Message> getMessagesByUser(Integer accountId) {
        return messageRepository.findByPostedBy(accountId);
    }

    public int updateMessageText(Integer messageId, String newText) {
        if (newText == null || newText.isEmpty()) {
            throw new IllegalArgumentException("New message text cannot be blank.");
        }
        
        Optional<Message> messageOpt = messageRepository.findById(messageId);
        if (!messageOpt.isPresent()) {
            throw new IllegalArgumentException("Message not found."); 
        }
        
        Message message = messageOpt.get();
        message.setMessageText(newText);
        messageRepository.save(message);
        return 1; 
    }
    
    
    
}

