package com.craig.email;

import java.util.Map;

/**
 * Created by craig on 8/26/16.
 */
public class EmailObj {
    private final String from;
    private final String to;
    private final Map<String, Object> body;
    private final String subject;
    private final String template;

    public EmailObj(String from, String to, Map<String, Object> body, String subject, String template) {
        this.from = from;
        this.to = to;
        this.body = body;
        this.subject = subject;
        this.template = template;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public Map<String, Object> getBody() {
        return body;
    }

    public String getSubject() {
        return subject;
    }

    public String getTemplate() {
        return template;
    }
}
