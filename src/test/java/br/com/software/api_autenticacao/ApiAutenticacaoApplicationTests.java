package br.com.software.api_autenticacao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Fail.fail;

@SpringBootTest
class ApiAutenticacaoApplicationTests {

	@Test
	@DisplayName("TS01 - Deve criar um usuário com sucesso na API.")
	void deveCriarUsuarioComSucesso() {
		fail("Não implementado");
	}

	@Test
	@DisplayName("TS02 - Não deve permitir a criação de usuário com email já cadastrado no sistema.")
	void naoDeveCriarUsuarioComEmailJaCadastrado() {
		fail("Não implementado.");
	}

	@Test
	@DisplayName("TS03 - Não deve permitir a criação de usuário com senha fraca.")
	void naoDeveCriarUsuarioComSenhaFraca() {
		fail("Não implementado.");
	}

	@Test
	@DisplayName("TS04 - Deve autenticar usuário com sucesso.")
	void deveAutenticarUsuarioComSucesso() {
		fail("Não implementado.");
	}

	@Test
	@DisplayName("TS05 - Não deve autenticar usuário com email e senha inválida.")
	void naoDeveAutenticarUsuarioComEmailESenhaInvalida() {
		fail("Não implementado");
	}
}
