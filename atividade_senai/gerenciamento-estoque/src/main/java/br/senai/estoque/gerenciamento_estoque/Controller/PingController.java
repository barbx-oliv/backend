package br.senai.estoque.gerenciamento_estoque.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/ping") // define a url 
public class PingController {
    
    @GetMapping // responde o GET, respondendo direto em /ping
    public String ping(){
        return "ok";
    }
}
