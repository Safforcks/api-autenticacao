package br.com.software.api_autenticacao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Fail.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc // Habilitar a biblioteca MockMvc do Sprint Boot
@ActiveProfiles("test") // Apontando o arquivo application-test.yaml
class ApiAutenticacaoApplicationTests {

	// Injeção de dependência
	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("TS01 - Deve criar um usuário com sucesso na API.")
	void deveCriarUsuarioComSucesso() throws Exception {
		// ARRANGE
		var request = """
				{
					"nome": "João Pedro",
					"email": "joão@gmail.com",
					"senha": "João123*"
				}
				""";

		// ACT
		var response = mockMvc.perform(post("/api/v1/usuario/criar")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(request));

		// ASSERT
		response.andExpect(status().isCreated());
		response.andExpect(jsonPath("$.mensagem").value("Usuário cadastrado com sucesso"));
		response.andExpect(jsonPath("$.nome").value("João Pedro"));
		response.andExpect(jsonPath("$.email").value("joão@gmail.com"));
		response.andExpect(jsonPath("$.perfil").value("Operador"));
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
