package com.craig.email;

public class EmailObjBuilder {
    private String from;
    private String to;
    private String body;
    private String subject;
    private boolean htmlType;

    public EmailObjBuilder from(String from) {
        this.from = from;
        return this;
    }

    public EmailObjBuilder to(String to) {
        this.to = to;
        return this;
    }

    public EmailObjBuilder body(String body) {
        this.body = body;
        return this;
    }

    public EmailObjBuilder htmlType(boolean htmlType) {
        this.htmlType = htmlType;
        return this;
    }
    public EmailObjBuilder subject(String subject) {
        this.subject = subject;
        return this;
    }

    public EmailObj createEmailObj() {
        return new EmailObj(from, to, body, subject, htmlType);
    }
}