package org.generation.blogPessoal.model.dtos;

public class UsuarioCredentialsDTO {

	private Long id;
	private String email;
	private String token;
	private String tokenBasic;
	
	private String nome;
	private String foto;
	private String tipoUsuario;
	

	public UsuarioCredentialsDTO() {
	}

	public UsuarioCredentialsDTO(String email, String token, String tokenBasic) {
		this.email = email;
		this.token = token;
		this.tokenBasic = tokenBasic;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokenBasic() {
		return tokenBasic;
	}

	public void setTokenBasic(String tokenBasic) {
		this.tokenBasic = tokenBasic;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
