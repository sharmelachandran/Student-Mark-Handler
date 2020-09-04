package com.example.demo;

public class msges {
    String messages;

    public msges(String messages) {
        this.messages = messages;
    }

    public msges() {
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }
    public String toString(){
        return this.messages+".";
    }
}
