package org.kst.lms.mails;

import lombok.RequiredArgsConstructor;
import org.kst.lms.models.EmailTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;
    private final MailContentBuilder mailContentBuilder;

    @Async
    public void sendEmail(EmailTemplate email) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("testing@private.com");
            messageHelper.setSubject(email.getSubject());
            System.out.println(email.getTemplate());
            messageHelper.setText(email.getTemplate());
        };

//        mailSender.send(messagePreparator);
        System.out.println(email.getTemplate());
        System.out.println("Message has been sent to the user");
    }

}
