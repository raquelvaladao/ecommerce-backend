package com.api.estudo.filter;

import com.api.estudo.entities.Usuario;
import com.api.estudo.services.AutenticacaoService;
import com.api.estudo.services.UsuarioService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AutenticacaoFilter extends OncePerRequestFilter {

    private final UsuarioService usuarioService;
    private final AutenticacaoService autenticacaoService;

    public AutenticacaoFilter(UsuarioService usuarioService, AutenticacaoService autenticacaoService) {
        this.usuarioService = usuarioService;
        this.autenticacaoService = autenticacaoService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        String token = null;
        if(header != null && header.startsWith("Bearer ")){
            token = header.substring(7);
        }

        if(autenticacaoService.verificarToken(token)){
            Long idUsuario = autenticacaoService.retornarIdUsuario(token);
            Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario);
            SecurityContextHolder.getContext()
                    .setAuthentication(new UsernamePasswordAuthenticationToken(
                            usuario,
                            null,
                            usuario.getAuthorities())
                    );
        }

        filterChain.doFilter(request, response);
    }
}
