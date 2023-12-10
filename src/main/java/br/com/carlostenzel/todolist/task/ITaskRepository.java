package br.com.carlostenzel.todolist.task;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ITaskRepository extends JpaRepository<TaskModel, UUID> {
    // Criamos a lista de tarefas pelo usuário.
    List<TaskModel> findByIdUser(UUID idUser);       
    
    // Um modo alternativo de fazer a validação de dono da tarefa.
    // TaskModel findByIdAndIdUser(UUID id, UUID idUser);
}
