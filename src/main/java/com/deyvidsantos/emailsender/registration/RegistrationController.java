package com.deyvidsantos.emailsender.registration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    /**
     * Endpoint para registrar um novo usuário.
     * 
     * @param request A solicitação de registro contendo os dados do usuário.
     * @return O token de confirmação gerado para o usuário.
     */
    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    /**
     * Endpoint para confirmar o registro de um usuário através do token de
     * confirmação.
     * 
     * @param token O token de confirmação enviado por email.
     * @return Uma mensagem indicando o status da confirmação.
     */
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

}
