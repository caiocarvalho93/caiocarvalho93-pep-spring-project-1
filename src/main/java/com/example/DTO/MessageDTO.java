package com.example.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageDTO {

    @JsonProperty("messageId")
    private Integer messageId;

    @JsonProperty("postedBy")
    private Integer postedBy;

    @JsonProperty("messageText")
    private String messageText;

    @JsonProperty("timePostedEpoch")
    private Long timePostedEpoch;

    public MessageDTO() {
    }

    public MessageDTO(Integer messageId, Integer postedBy, String messageText, Long timePostedEpoch) {
        this.messageId = messageId;
        this.postedBy = postedBy;
        this.messageText = messageText;
        this.timePostedEpoch = timePostedEpoch;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(Integer postedBy) {
        this.postedBy = postedBy;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Long getTimePostedEpoch() {
        return timePostedEpoch;
    }

    public void setTimePostedEpoch(Long timePostedEpoch) {
        this.timePostedEpoch = timePostedEpoch;
    }
}
