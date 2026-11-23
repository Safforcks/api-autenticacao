package br.com.software.api_autenticacao.services;

import br.com.software.api_autenticacao.dtos.CriarUsuarioRequest;
import br.com.software.api_autenticacao.dtos.CriarUsuarioResponse;
import br.com.software.api_autenticacao.entities.Perfil;
import br.com.software.api_autenticacao.entities.Usuario;
import br.com.software.api_autenticacao.exceptions.EmailJaCadastradoException;
import br.com.software.api_autenticacao.repositories.PerfilRepository;
import br.com.software.api_autenticacao.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.HexFormat;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    // Método para implementar o fluxo de cadastro do usuário
    public CriarUsuarioResponse criarUsuario(CriarUsuarioRequest request) {

        // Verificar se o email já está cadastrado no banco de dados
        if (usuarioRepository.findByEmail(request.email()) != null) {
            throw new EmailJaCadastradoException();
        }

        var usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(criptografarSenha(request.senha()));

        // Todo usuário que se cadastrar será vinculado a um perfil chamado "Operador"
        var perfil = perfilRepository.findByNome("Operador");

        // Se o perfil não existir, iremos cadastrá-lo
        if (perfil == null) {
            perfil = new Perfil();
            perfil.setNome("Operador");
            perfilRepository.save(perfil);
        }

        // Associar o usuário ao perfil operador
        usuario.setPerfil(perfil);

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

    // Método privado auxiliar para fazer a criptografia da senha do usuário
    private String criptografarSenha(String senha) {
        try {
            MessageDigest algoritmo = MessageDigest.getInstance("SHA-256");
            byte[] hash = algoritmo.digest(senha.getBytes(StandardCharsets.UTF_8)
            );

            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 não disponível.", e);
        }
    }
}
