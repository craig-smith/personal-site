package com.craig.email;

/**
 * Created by craig on 8/26/16.
 */
public class EmailObj {
    private final String from;
    private final String to;
    private final String body;
    private final String subject;
    private final boolean htmlType;

    public EmailObj(String from, String to, String body, String subject, boolean htmlType) {
        this.from = from;
        this.to = to;
        this.body = body;
        this.subject = subject;
        this.htmlType = htmlType;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getBody() {
        return body;
    }

    public String getSubject() {
        return subject;
    }

    public boolean isHtmlType() {
        return htmlType;
    }
}
