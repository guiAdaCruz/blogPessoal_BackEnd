package org.generation.blogPessoal.model.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

public class UsuarioSignUpDTO {
	
	@NotBlank (message = "O atributo Nome é obrigatório!")
	@Size(min = 3, max = 155)
    private  String nomeCompleto;
	
	@Schema(example = "nome@email.com")
	@Email (message = "O atributo Email é obrigatório!")
    private String email;
	
	@NotBlank(message = "O atributo Senha é obrigatório!")
	@Size(min = 5, max = 30)
	private String senha;

	public UsuarioSignUpDTO() {
	}
		
	public UsuarioSignUpDTO(String email, String nomeCompleto, String senha) {
		this.email = email;
		this.nomeCompleto = nomeCompleto;
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
