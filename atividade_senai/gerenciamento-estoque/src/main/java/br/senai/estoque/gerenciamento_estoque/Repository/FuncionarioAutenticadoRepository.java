package br.senai.estoque.gerenciamento_estoque.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.senai.estoque.gerenciamento_estoque.Model.FuncionarioAutenticado;

public interface FuncionarioAutenticadoRepository extends JpaRepository<FuncionarioAutenticado, Long> {
    
    // O Spring traduz esse nome para: SELECT count(*) FROM funcionario_autenticado WHERE nif = ? AND nome = ? AND ativo = true
    boolean existsByNifAndNomeAndAtivoTrue(String nif, String nome);
}