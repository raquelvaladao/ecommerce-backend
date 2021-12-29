package com.api.estudo.services;


import com.api.estudo.dto.request.RequestLoginDTO;
import com.api.estudo.dto.response.ResponseTokenDTO;
import com.api.estudo.entities.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class AutenticacaoService {

    @Value("${api.jwt.expiration}")
    private String expiration;

    @Value("${api.jwt.secret}")
    private String secret;

    @Value("${api.jwt.issuer}")
    private String issuer;

    private final AuthenticationManager authenticationManager;

    public AutenticacaoService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public ResponseTokenDTO autenticar(RequestLoginDTO login){
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getLogin(), login.getSenha()));
        String token = gerarToken(authenticate);
        return new ResponseTokenDTO(token);
    }

    private String gerarToken(Authentication authentication) {
        Usuario usuarioALogar = (Usuario) authentication.getPrincipal();
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));


        return JWT.create()
                .withSubject(usuarioALogar.getId().toString())
                .withIssuer(issuer)
                .withExpiresAt(dataExpiracao)
                .sign(this.criarAlgoritmo());
    }

    private Algorithm criarAlgoritmo() {
        return Algorithm.HMAC256(secret);
    }

    public boolean verificarToken(String token){
        try {
            if (token == null)
                return false;

            JWT.require(this.criarAlgoritmo()).withIssuer(issuer).build().verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public Long retornarIdUsuario(String token) {
        String subject = JWT.require(this.criarAlgoritmo()).withIssuer(issuer).build().verify(token).getSubject();

        return Long.parseLong(subject);

    }


}
