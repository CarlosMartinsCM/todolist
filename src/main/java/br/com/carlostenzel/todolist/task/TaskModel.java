package br.com.carlostenzel.todolist.task;

// import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

// import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

    /* 
        - ID, pois como será um dado dentro do banco precisamos ter um id;
        
        - Um usuário (ID_USUARIO);
        
        - Uma descrição;
        
        - Título;
        
        - Data de início, quando a tarefa irá se iniciar;
        
        - Data de término;
        
        - Prioridade da tarefa;
    */

@Data
@Entity(name = "tb_tasks")
public class TaskModel {


    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String description;

    @Column(length = 50)
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    @Column(length = 50)
    private String priority;

    private UUID idUser;


    @CreationTimestamp
    private LocalDateTime createdAt;

    // Método para validar quantidade de caracteres do campo title, 50.
    public void setTitle(String title) throws Exception{
        if(title.length() > 50){
            // Aqui criamos a exceção.
            throw new Exception("O campo title de ter no máximo 50 caracteres.");
        }
        this.title = title;
    }

}
