package dan.ms.tp.msusuarios.rest.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import dan.ms.tp.msusuarios.modelo.Usuario;
import dan.ms.tp.msusuarios.rest.service.UsuarioService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UsuarioService usuarioService;

  @Test
  public void getAllUsuarioTest() throws Exception {
    Usuario user1 = new Usuario();
    user1.setId(1);
    user1.setUserName("user1");

    Usuario user2 = new Usuario();
    user2.setId(2);
    user2.setUserName("user2");

    when(usuarioService.getAllUsuarios()).thenReturn(Arrays.asList(user1, user2));

    mockMvc.perform(MockMvcRequestBuilders.get("/api/usuario")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].id", is(1)))
        .andExpect(jsonPath("$[0].userName", is("user1")))
        .andExpect(jsonPath("$[1].id", is(2)))
        .andExpect(jsonPath("$[1].userName", is("user2")));
  }
}