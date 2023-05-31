package com.deyvidsantos.emailsender.registration.token;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    /**
     * Salva um ConfirmationToken no banco de dados.
     *
     * @param token o ConfirmationToken a ser salvo
     */
    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    /**
     * Obtém um ConfirmationToken com base no token.
     *
     * @param token o token do ConfirmationToken a ser buscado
     * @return um Optional contendo o ConfirmationToken, se encontrado
     */
    public Optional<ConfirmationToken> getByToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    /**
     * Define a data de confirmação de um ConfirmationToken.
     *
     * @param token o token do ConfirmationToken
     * @return o número de linhas afetadas pela atualização
     */
    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }
}
