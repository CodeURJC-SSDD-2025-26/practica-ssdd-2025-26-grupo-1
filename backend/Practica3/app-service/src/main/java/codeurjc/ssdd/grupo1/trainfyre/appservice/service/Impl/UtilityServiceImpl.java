package codeurjc.ssdd.grupo1.trainfyre.appservice.service.Impl;

import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.EmailRequest;
import codeurjc.ssdd.grupo1.trainfyre.appservice.service.UtilityService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class UtilityServiceImpl implements UtilityService {

    @Value("${email.service.url}")
    private String emailServiceUrl;

    @Override
    public void sendEmail(String[] to, String subject, String body) {
        RestClient.create()
                .method(org.springframework.http.HttpMethod.POST)
                .uri(emailServiceUrl + "/api/v1/emails/sendEmail")
                .body(new EmailRequest(List.of(to), subject, body))
                .retrieve()
                .body(Void.class);
    }
}
