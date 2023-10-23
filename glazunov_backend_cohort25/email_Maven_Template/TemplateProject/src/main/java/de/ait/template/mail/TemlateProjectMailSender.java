package de.ait.template.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


/**
 * 24.10.2023
 * TemplateProject
 *
 * @author Konstantin Glazunov (AIT TR)
 */

@Component
@RequiredArgsConstructor
public class TemlateProjectMailSender {

    private final JavaMailSender javaMailSender;

    public void sendMail(String email, String text, String subject){
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");

        try{
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(text);
        helper.setFrom("TemplateProjectAdmin");
        }catch (MessagingException e) {
            throw  new IllegalStateException(e);
        }
    }

}
