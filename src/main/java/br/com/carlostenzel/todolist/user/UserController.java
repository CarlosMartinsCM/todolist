package br.com.carlostenzel.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

/* Modificadores de acesso
 * public - Qualquer um pode acessar a classe
 * protected - Só quem está na mesma extrutura do pacote tem acesso.
 * private - Apenas alguns atributos conseguem acessar a classe.

*/
@RestController
@RequestMapping("/users")
public class UserController {

    // Essa notação pede para que o spring gerêncie o ciclo de vida do userRepository.
    @Autowired
    private IUserRepository userRepository;

    // Aqui printamos o resultado da requisição.
    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel UserModel) {
        var user = this.userRepository.findByUsername(UserModel.getUsername()); 

        if(user != null) {
            // Mensagem de erro
            // status code
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existente");
        }

        var passwordHashred = BCrypt.withDefaults().hashToString(12, UserModel.getPassword().toCharArray());

        UserModel.setPassword(passwordHashred);

        var userCreated = this.userRepository.save(UserModel);
        return ResponseEntity.status(HttpStatus.OK).body(userCreated);
    }
}

/**
     * Retornos
     * String - Trabalhar com textos;
     * Integer - Trabalhar com números inteiros;
     * Double - Trabalhar com números com casas decimais;
     * Float - Trabalhar com números com casas decimais com menos caracteres;
     * char - Trabalhar com caracteres;
     * Date - Trabalhar com datas;
     * void -  Quando não precisamos de nenhum retorno do método.
     **/