package br.senai.estoque.gerenciamento_estoque.Controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import br.senai.estoque.gerenciamento_estoque.service.FuncionarioService;

@Controller
public class AuthController {
	@Autowired
	private FuncionarioService funcionarioService;

	@GetMapping("/login")
	public String loginPage() {
		return "auth/login"; // templates/auth/login.html
	}

	@PostMapping("/login")
	public String login(@RequestParam String nif,
						@RequestParam String senha,
						HttpSession session,
						Model model) {

		boolean credenciaisOk = funcionarioService.validarLogin(nif, senha);
		if (!credenciaisOk) {
			model.addAttribute("erro", "NIF ou senha inválidos.");
			return "auth/login";
		}

		// Sessão simples: marca que o usuário está logado
		session.setAttribute("usuarioLogado", true);
		session.setAttribute("nif", nif);

		// Após login, manda para a página interna
		return "redirect:/app";
	}

	@GetMapping("/cadastro")
	public String cadastroPage() {
		return "auth/cadastro"; // templates/auth/cadastro.html
	}

	@PostMapping("/cadastro")
	public String cadastro(@RequestParam String nome,
						  @RequestParam String nif,
						  @RequestParam String senha,
						  Model model) {
		
		String resultado = funcionarioService.cadastrar(nome, nif, senha);
		model.addAttribute("sucesso", "Cadastro realizado! Volte e faça seu login.");
		if (resultado.equals("sucesso")) {
		return "auth/login";
		} else {
			model.addAttribute("erro", "Implementar cadastro no Service.");
		return "auth/cadastro";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}

