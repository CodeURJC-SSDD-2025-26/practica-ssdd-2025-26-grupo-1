package codeurjc.ssdd.grupo1.trainfyre.service;

import java.util.List;

public interface EmailService {

    public void sendEmail(String[] to, String subject, String body);
}
