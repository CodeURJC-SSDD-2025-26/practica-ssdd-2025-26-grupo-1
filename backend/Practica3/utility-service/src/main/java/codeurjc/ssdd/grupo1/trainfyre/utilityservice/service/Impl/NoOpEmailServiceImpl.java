package codeurjc.ssdd.grupo1.trainfyre.utilityservice.service.Impl;

import codeurjc.ssdd.grupo1.trainfyre.utilityservice.service.EmailService;
import org.springframework.stereotype.Service;

@Service
public class NoOpEmailServiceImpl implements EmailService {

    @Override
    public void sendEmail(String[] to, String subject, String body) {
        // No hacemos nada, implementación vacía cuando el email no está configurado (no se usa perfil "mail")
    }
}
