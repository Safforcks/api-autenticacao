package br.com.software.api_autenticacao.services;

import br.com.software.api_autenticacao.dtos.CriarUsuarioRequest;
import br.com.software.api_autenticacao.dtos.CriarUsuarioResponse;
import br.com.software.api_autenticacao.entities.Usuario;
import br.com.software.api_autenticacao.repositories.PerfilRepository;
import br.com.software.api_autenticacao.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    // Método para implementar o fluxo de cadastro do usuário
    public CriarUsuarioResponse criarUsuario(CriarUsuarioRequest request) {
        var usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(request.senha());

        usuarioRepository.save(usuario);

        return new CriarUsuarioResponse(
                "Usuário cadastrado com sucesso",
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getPerfil().getNome(),
                LocalDateTime.now()
        );
    }

    // Método para implementar o fluxo de autenticação do usuário
    public void autenticarUsuario() {}

}
