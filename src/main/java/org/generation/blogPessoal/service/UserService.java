package org.generation.blogPessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.generation.blogPessoal.model.UsuarioModel;
import org.generation.blogPessoal.model.dtos.UsuarioCredentialsDTO;
import org.generation.blogPessoal.model.dtos.UsuarioLoginDTO;
import org.generation.blogPessoal.model.dtos.UsuarioSignUpDTO;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

	private @Autowired UsuarioRepository repository;
	private UsuarioModel usuario;
	private UsuarioCredentialsDTO credencias;

	private static String encriptarSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(senha);
	}

	private static String geradorToken(String email, String senha) {
		String estrutura = email + ":" + senha;
		byte[] token = Base64.encodeBase64(estrutura.getBytes(Charset.forName("US-ASCII")));
		return new String(token);
	}

	private static String geradorTokenBasic(String email, String senha) {
		String estrutura = email + ":" + senha;
		byte[] token = Base64.encodeBase64(estrutura.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(token);
	}

	public ResponseEntity<UsuarioModel> cadastrarUsuario(UsuarioSignUpDTO novoUsuario) {
		Optional<UsuarioModel> optional = repository.findByEmail(novoUsuario.getEmail());
		if (optional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já cadastrado!");
		} else {
			usuario = new UsuarioModel();
			usuario.setNome(novoUsuario.getNomeCompleto());
			usuario.setEmail(novoUsuario.getEmail());
			usuario.setSenha(encriptarSenha(novoUsuario.getSenha()));
			usuario.setToken(geradorToken(novoUsuario.getEmail(), novoUsuario.getSenha()));
			return ResponseEntity.status(201).body(repository.save(usuario));
		}
	}

	public ResponseEntity<UsuarioCredentialsDTO> logarUsuario(UsuarioLoginDTO usuario) {
		return repository.findByEmail(usuario.getEmail()).map(resp -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if (encoder.matches(usuario.getSenha(), resp.getSenha())) {
				credencias = new UsuarioCredentialsDTO();
				credencias.setId(resp.getId());
				credencias.setEmail(resp.getEmail());
				credencias.setToken(resp.getToken());
				credencias.setTokenBasic(geradorTokenBasic(usuario.getEmail(), usuario.getSenha()));

				return ResponseEntity.status(200).body(credencias);
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha incorreta");
			}

		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email não encontrado"));

	}
}
