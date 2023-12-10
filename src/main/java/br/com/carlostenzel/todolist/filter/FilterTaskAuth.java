package br.com.carlostenzel.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.carlostenzel.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var servletPath = request.getServletPath();

        System.out.println("PAHT" + servletPath);

        // Pega apenas a rota tasks para fazer a verificação.
        if (servletPath.startsWith("/tasks/")) {
            // Pegar a autenticação (usuario e senha)
            var authorization = request.getHeader("Authorization");

            // Exclui a palavra Basic
            var authEncoded = authorization.substring("Basic".length()).trim();

            // Decodifica o Basic64
            byte[] authDecode = Base64.getDecoder().decode(authEncoded);

            // Converte em uma nova string o resultado do decode
            var authString = new String(authDecode);

            // Separa as credenciais em Array, com indice [0] para usuario e indice [1] para
            // password.
            String[] credentials = authString.split(":");
            String username = credentials[0];
            String password = credentials[1];

            // System.out.println("Authorization");
            // System.out.println(username);
            // System.out.println(password);

            // Validar usuario
            var user = this.userRepository.findByUsername(username);
            if (user == null) {
                response.sendError(401);
            } else {
                // Validar senha
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if (passwordVerify.verified) {
                    request.setAttribute("idUser", user.getId());
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401);
                }

                // Seguir adiante na aplicação

            }
        } else {
            filterChain.doFilter(request, response);
        }

    }
}
