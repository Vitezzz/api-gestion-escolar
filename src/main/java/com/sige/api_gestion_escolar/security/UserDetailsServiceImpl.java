package com.sige.api_gestion_escolar.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.sige.api_gestion_escolar.entity.Usuario;
import com.sige.api_gestion_escolar.repository.UsuarioRepository;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    private final UsuarioRepository usuarioRepository;
    
    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
        
        List<GrantedAuthority> authorities = usuario.getUsuarioRoles().stream()
                .map(usuarioRol -> new SimpleGrantedAuthority("ROLE_" + usuarioRol.getRol().getNombre()))
                .collect(Collectors.toList());

                // DEBUG TEMPORAL (Borrar después)
System.out.println("=== DEBUG LOGIN ===");
System.out.println("Usuario encontrado: " + usuario.getUsername());
System.out.println("Hash en DB: " + usuario.getPasswordHash());
System.out.println("===================");
        
        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPasswordHash())
                .authorities(authorities)
                .disabled(!usuario.getActivo())
                .build();
    }
}
