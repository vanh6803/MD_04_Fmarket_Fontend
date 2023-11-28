package com.example.hn_2025_online_shop.model;

public class PeopleMsg {
    private Store store;
    private User account;
    private Message latestMessage;

    public PeopleMsg() {
    }

    public PeopleMsg(Store store, User account, Message latestMessage) {
        this.store = store;
        this.account = account;
        this.latestMessage = latestMessage;
    }

    @Override
    public String toString() {
        return "PeopleMsg{" +
                "store=" + store +
                ", account=" + account +
                ", latestMessage=" + latestMessage +
                '}';
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public User getAccount() {
        return account;
    }

    public void setAccount(User account) {
        this.account = account;
    }

    public Message getLatestMessage() {
        return latestMessage;
    }

    public void setLatestMessage(Message latestMessage) {
        this.latestMessage = latestMessage;
    }
}
