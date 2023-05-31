package com.deyvidsantos.emailsender.appuser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    /**
     * Busca um usuário pelo email.
     *
     * @param email o email do usuário
     * @return um Optional contendo o usuário, se encontrado
     */
    Optional<AppUser> findByEmail(String email);

    /**
     * Habilita um usuário no aplicativo.
     *
     * @param email o email do usuário a ser habilitado
     * @return o número de linhas afetadas pela atualização
     */
    @Transactional
    @Modifying
    @Query("UPDATE AppUser a SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);
}
