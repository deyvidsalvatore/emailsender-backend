package com.deyvidsantos.emailsender.appuser;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.deyvidsantos.emailsender.registration.token.ConfirmationToken;
import com.deyvidsantos.emailsender.registration.token.ConfirmationTokenService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    /**
     * Implementação do método da interface UserDetailsService.
     * Carrega os detalhes de um usuário pelo email.
     *
     * @param email o email do usuário
     * @return os detalhes do usuário
     * @throws UsernameNotFoundException se o usuário não for encontrado
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND_MSG, email)));
    }

    /**
     * Registra um novo usuário no aplicativo.
     *
     * @param appUser o usuário a ser registrado
     * @return a resposta HTTP com o resultado da criação do usuário
     */
    public String signUpUser(AppUser appUser) {
        Optional<AppUser> userOptional = appUserRepository.findByEmail(appUser.getEmail());
        boolean userExists = userOptional.isPresent();

        if (userExists) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Email already taken");
            return "Email already taken!";
        }

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser);

        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }

    /**
     * Habilita um usuário no aplicativo.
     *
     * @param email o email do usuário a ser habilitado
     * @return o número de linhas afetadas pela atualização
     */
    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }
}
