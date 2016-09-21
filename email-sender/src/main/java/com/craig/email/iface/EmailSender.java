package com.craig.email.iface;

import com.craig.email.EmailObj;

/**
 * Created by craig on 8/26/16.
 */
public interface EmailSender {
    void sendMail(EmailObj emailObj);
}
