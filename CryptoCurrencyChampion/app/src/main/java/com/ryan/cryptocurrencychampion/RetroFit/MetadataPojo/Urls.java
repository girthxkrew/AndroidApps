package com.ryan.cryptocurrencychampion.RetroFit.MetadataPojo;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Urls {

    @SerializedName("website")
    @Expose
    private List<String> website = null;
    @SerializedName("technical_doc")
    @Expose
    private List<String> technical_doc = null;
    @SerializedName("twitter")
    @Expose
    private List<Object> twitter = null;
    @SerializedName("reddit")
    @Expose
    private List<String> reddit = null;
    @SerializedName("message_board")
    @Expose
    private List<String> message_board = null;
    @SerializedName("announcement")
    @Expose
    private List<Object> announcement = null;
    @SerializedName("chat")
    @Expose
    private List<Object> chat = null;
    @SerializedName("explorer")
    @Expose
    private List<String> explorer = null;
    @SerializedName("source_code")
    @Expose
    private List<String> source_code = null;

    public List<String> getWebsite() {
        return website;
    }

    public void setWebsite(List<String> website) {
        this.website = website;
    }

    public List<String> getTechnical_doc() {
        return technical_doc;
    }

    public void setTechnical_doc(List<String> technical_doc) {
        this.technical_doc = technical_doc;
    }

    public List<Object> getTwitter() {
        return twitter;
    }

    public void setTwitter(List<Object> twitter) {
        this.twitter = twitter;
    }

    public List<String> getReddit() {
        return reddit;
    }

    public void setReddit(List<String> reddit) {
        this.reddit = reddit;
    }

    public List<String> getMessage_board() {
        return message_board;
    }

    public void setMessage_board(List<String> message_board) {
        this.message_board = message_board;
    }

    public List<Object> getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(List<Object> announcement) {
        this.announcement = announcement;
    }

    public List<Object> getChat() {
        return chat;
    }

    public void setChat(List<Object> chat) {
        this.chat = chat;
    }

    public List<String> getExplorer() {
        return explorer;
    }

    public void setExplorer(List<String> explorer) {
        this.explorer = explorer;
    }

    public List<String> getSource_code() {
        return source_code;
    }

    public void setSource_code(List<String> source_code) {
        this.source_code = source_code;
    }

}
