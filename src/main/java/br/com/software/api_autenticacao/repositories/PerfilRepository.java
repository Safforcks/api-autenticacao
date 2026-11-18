package br.com.software.api_autenticacao.repositories;

import br.com.software.api_autenticacao.entities.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer> {

}
