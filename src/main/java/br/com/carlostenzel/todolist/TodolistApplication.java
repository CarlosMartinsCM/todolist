// Aqui temos o pacote do projeto
package br.com.carlostenzel.todolist;

// Aqui temos as classes que serão utilizadas e reutilizadas no projeto.
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Por exemplo, para utilizarmos o SpringBootApplication precisamos antes importa-lo.
// O @ significa anotation, e tem uma função relativa a ela, para executar alguma ação.
// Neste caso define que a todolist é a classe inicial do projeto.
@SpringBootApplication
public class TodolistApplication {

	public static void main(String[] args) {
		// Aqui rodamos o projeto através do tomcat, rodando o SpringApplication.run com o todolist como argumento.
		SpringApplication.run(TodolistApplication.class, args);
	}

}
