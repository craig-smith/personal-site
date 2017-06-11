package com.craig.email;

import java.util.Map;

public class EmailObjBuilder {
    private String from;
    private String to;
    private Map<String, Object> body;
    private String subject;
    private String template;

    public EmailObjBuilder from(String from) {
        this.from = from;
        return this;
    }

    public EmailObjBuilder to(String to) {
        this.to = to;
        return this;
    }

    public EmailObjBuilder body(Map<String, Object> body) {
        this.body = body;
        return this;
    }

    public EmailObjBuilder template(String template) {
        this.template = template;
        return this;
    }
    public EmailObjBuilder subject(String subject) {
        this.subject = subject;
        return this;
    }

    public EmailObj createEmailObj() {
        return new EmailObj(from, to, body, subject, template);
    }
}