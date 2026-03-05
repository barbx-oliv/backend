package com.example.rh.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.rh.Model.Funcionario;
import java.util.List;

public interface FuncionarioRepository extends CrudRepository<Funcionario, Long> {
    // Métodos para realizar o crud do Funcionario
    // Criar uma busca pela chave primaria do Funcionario
    Funcionario findById (long id);

    // Busca pelo nome 
    Funcionario finfByNome (String nome);

    // Busca para vários nomes // nn existe no JPA
    @Query(value = "select u from Funcionario u where u.nome like %?1%")
    List<Funcionario> findByNomes(String nome);
}
