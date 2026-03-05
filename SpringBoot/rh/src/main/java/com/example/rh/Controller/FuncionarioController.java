package com.example.rh.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.rh.Model.Funcionario;
import com.example.rh.Repository.FuncionarioRepository;

@Controller
public class FuncionarioController {
    // Atributo
    @Autowired
    private FuncionarioRepository fr;
    
    //quando o endereço /funcionario for digitado na bara de endereço
    // o site será direcionado para a página de cadastro de funcinário
    @RequestMapping(value="/funcionario", method=RequestMethod.GET)
    public String requestMethodName(@RequestParam String param) {
        return "funcionario/funcionario";
    }
    
    // requisição para cadastrar funcionário no banco 
    @RequestMapping(value = "/funcionario", method = RequestMethod.POST)
    public String gravarFuncionario (Funcionario funcionario){
        fr.save(funcionario);
        return "redirect:/funcionario";
    }
}
