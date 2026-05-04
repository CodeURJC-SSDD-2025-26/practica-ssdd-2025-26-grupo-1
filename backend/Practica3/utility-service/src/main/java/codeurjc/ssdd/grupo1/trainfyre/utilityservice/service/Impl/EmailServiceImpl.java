package codeurjc.ssdd.grupo1.trainfyre.utilityservice.service.Impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import codeurjc.ssdd.grupo1.trainfyre.utilityservice.service.EmailService;

@Service
@Primary
@Profile("mail")
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final String eMailUserName;

    public EmailServiceImpl(JavaMailSender mailSender, @Value("${spring.mail.username}") String eMailUserName) {
        this.mailSender = mailSender;
        this.eMailUserName = eMailUserName;
    }

    @Override
    public void sendEmail(String[] to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom(eMailUserName);
        mailSender.send(message);
    }
}
