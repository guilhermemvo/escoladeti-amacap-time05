/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unicesumar.time5.service;

import br.unicesumar.time5.entity.EmailSender;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 *
 * @author TiagoH
 */
public class EmailService {

    private MailSender mailSender;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(EmailSender email) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("monicafuzibybadadasisin2@gmail.com");
        message.setTo("monicafuzibybadadasisin2@gmail.com");
        message.setSubject(email.getAssunto());
        message.setText(email.getMensagem());
        mailSender.send(message);
    }

}