package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

 @RestController
 @RequestMapping
public class SocialMediaController {

    @Autowired
    private AccountService accountService;
    
    @Autowired
    private MessageService messageService;

    @PostMapping("/register")
    public ResponseEntity<Account> registerAccount(@RequestBody Account account) {
        try {
            Account newAccount = accountService.register(account);
            return new ResponseEntity<>(newAccount, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); 
        }
        }
    //use object mapper like school project so no need to create DTO
        @PostMapping("/login") 
public ResponseEntity<Account> login(@RequestBody String json) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {

        JsonNode jsonNode = objectMapper.readTree(json);
        String username = jsonNode.get("username").asText();
        String password = jsonNode.get("password").asText();
        
        Account account = accountService.login(username, password);
        return new ResponseEntity<>(account, HttpStatus.OK);
    } catch (IllegalArgumentException e) {

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    } catch (Exception e) {

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
    
        @PostMapping("/messages")
        public ResponseEntity<Message> createMessage(@RequestBody Message message) {
            try {
                Message newMessage = messageService.createMessage(message);
                return new ResponseEntity<>(newMessage, HttpStatus.OK);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    
        @GetMapping("/messages")
        public ResponseEntity<List<Message>> getAllMessages() {
            List<Message> messages = messageService.getAllMessages();
            return new ResponseEntity<>(messages, HttpStatus.OK);
        }

        @GetMapping("/messages/{messageId}")
        public ResponseEntity<String> getMessageById(@PathVariable("messageId") Integer messageId) {
            Optional<Message> messageOpt = messageService.getMessageById(messageId);
            
            if (messageOpt.isPresent()) {
                return new ResponseEntity<>(messageOpt.get().getMessageText(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("", HttpStatus.OK); 
            }
        }
    
        @DeleteMapping("/messages/{messageId}")
        public ResponseEntity<Integer> deleteMessage(@PathVariable("messageId") Integer messageId) {
            int rowsDeleted = messageService.deleteMessageById(messageId);
            if (rowsDeleted > 0) {
                return new ResponseEntity<>(rowsDeleted, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
    
        @PatchMapping("/messages/{messageId}")
        public ResponseEntity<Integer> updateMessage(@PathVariable("messageId") Integer messageId, @RequestBody String newMessageText) {
            try {
                int rowsUpdated = messageService.updateMessageText(messageId, newMessageText);
                return new ResponseEntity<>(rowsUpdated, HttpStatus.OK);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
            }
        }
        
        
    
        @GetMapping("/accounts/{accountId}/messages")
        public ResponseEntity<List<Message>> getMessagesByAccountId(@PathVariable("accountId") Integer accountId) {
            List<Message> messages = messageService.getMessagesByUser(accountId);
            return new ResponseEntity<>(messages, HttpStatus.OK);
        }
    }



        















    


