package com.example.hn_2025_online_shop.model;

public class CommentAccount {
    private Comment comment;
    private User account;

    public CommentAccount() {
    }

    public CommentAccount(Comment comment, User account) {
        this.comment = comment;
        this.account = account;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getAccount() {
        return account;
    }

    public void setAccount(User account) {
        this.account = account;
    }
}
