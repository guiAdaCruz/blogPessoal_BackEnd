package org.generation.blogPessoal.security;

import java.util.Optional;

import org.generation.blogPessoal.model.UsuarioModel;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<UsuarioModel> user = userRepository.findByEmail(email);
		user.orElseThrow(()-> new UsernameNotFoundException(email + "não encontrado."));
		
		return user.map(UserDetailsImpl::new).get();
	}
	
		
	
}
