package br.senai.estoque.gerenciamento_estoque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.senai.estoque.gerenciamento_estoque.Model.Funcionario;
import br.senai.estoque.gerenciamento_estoque.Repository.FuncionarioAutenticadoRepository;
import br.senai.estoque.gerenciamento_estoque.Repository.FuncionarioRepository;
import java.util.Optional;

@Service
public class FuncionarioService {
   
    @Autowired
    private FuncionarioRepository funcionarioRepository; // Minúsculo

    @Autowired
    private FuncionarioAutenticadoRepository funcionarioAutenticadoRepository; // Minúsculo

    public String cadastrar(String nome, String nif, String senha) {
        // Agora o nome do método bate exatamente com a assinatura que criamos no Repository
        boolean autorizado = funcionarioAutenticadoRepository.existsByNifAndNomeAndAtivoTrue(nif, nome);
       
        if (!autorizado) {
            return "NIF ou Nome não encontrados na base de autorizados!";
        }

        if (funcionarioRepository.existsByNif(nif)) {
            return "Este NIF já possui uma conta cadastrada.";
        }

        Funcionario novo = new Funcionario();
        novo.setnome(nome);
        novo.setNif(nif);
        novo.setSenha(senha);

        funcionarioRepository.save(novo);
        return "sucesso";
    }

    public boolean validarLogin(String nif, String senha) {
        Optional<Funcionario> func = funcionarioRepository.findByNif(nif);
        return func.isPresent() && func.get().getSenha().equals(senha) && func.get().isAtivo();
    }
}