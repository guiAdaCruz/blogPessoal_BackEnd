package org.generation.blogPessoal.controller;

import javax.validation.Valid;

import org.generation.blogPessoal.model.UsuarioModel;
import org.generation.blogPessoal.model.dtos.UsuarioCredentialsDTO;
import org.generation.blogPessoal.model.dtos.UsuarioLoginDTO;
import org.generation.blogPessoal.model.dtos.UsuarioSignUpDTO;
import org.generation.blogPessoal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@CrossOrigin(origins = "*", allowedHeaders =  "*")
public class UserController {
	
	@Autowired
	private UserService usuarioService;
	
	@PostMapping("/login")
	 public ResponseEntity<UsuarioCredentialsDTO> logar (@Valid @RequestBody UsuarioLoginDTO usuario){
        return usuarioService.logarUsuario(usuario);
	}
	@PostMapping("/signup")
	public ResponseEntity<UsuarioModel> cadastrar(@Valid @RequestBody UsuarioSignUpDTO novoUsuario){
        return usuarioService.cadastrarUsuario(novoUsuario);
    }
	
}
