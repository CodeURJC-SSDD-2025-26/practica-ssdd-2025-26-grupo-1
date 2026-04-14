package codeurjc.ssdd.grupo1.trainfyre.service.Impl;

import codeurjc.ssdd.grupo1.trainfyre.service.EmailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoOpEmailService implements EmailService {

    @Override
    public void sendEmail(String[] to, String subject, String text) {
        //No hacemos nada, implementación vacía cunado el email no esta configurado(no se usa perfil email)
    }
}
