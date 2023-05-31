package com.deyvidsantos.emailsender.registration.token;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    /**
     * Encontra um ConfirmationToken pelo token.
     *
     * @param token o token a ser buscado
     * @return um Optional contendo o ConfirmationToken, se encontrado
     */
    Optional<ConfirmationToken> findByToken(String token);

    /**
     * Atualiza a data de confirmação de um ConfirmationToken.
     *
     * @param token       o token do ConfirmationToken
     * @param confirmedAt a data de confirmação a ser definida
     * @return o número de linhas afetadas pela atualização
     */
    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationToken c SET c.confirmedAt = ?2 WHERE c.token = ?1")
    int updateConfirmedAt(String token, LocalDateTime confirmedAt);
}
