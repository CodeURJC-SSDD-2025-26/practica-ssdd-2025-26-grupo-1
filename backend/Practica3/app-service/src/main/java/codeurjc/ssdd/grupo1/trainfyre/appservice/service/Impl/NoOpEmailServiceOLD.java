package codeurjc.ssdd.grupo1.trainfyre.appservice.service.Impl;

import codeurjc.ssdd.grupo1.trainfyre.appservice.service.EmailServiceOLD;
import org.springframework.stereotype.Service;

@Service
public class NoOpEmailServiceOLD implements EmailServiceOLD {

    @Override
    public void sendEmail(String[] to, String subject, String text) {
        //No hacemos nada, implementación vacía cunado el email no esta configurado(no se usa perfil email)
    }
}
