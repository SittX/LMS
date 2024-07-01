package org.kst.lms.mails;

import lombok.RequiredArgsConstructor;
import org.kst.lms.models.EmailTemplate;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;

    @Async
    public void sendEmail(EmailTemplate email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("Test");
        message.setSubject("Test Subject.");
        message.setText("Hello from Mail Text.");
        mailSender.send(message);
    }

}
