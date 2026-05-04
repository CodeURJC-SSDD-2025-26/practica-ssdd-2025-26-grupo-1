package codeurjc.ssdd.grupo1.trainfyre.utilityservice.service;

public interface EmailService {
    void sendEmail(String[] to, String subject, String body);
}
