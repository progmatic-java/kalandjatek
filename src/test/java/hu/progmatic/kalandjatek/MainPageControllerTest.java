package hu.progmatic.kalandjatek;

import hu.progmatic.kalandjatek.character.CharacterService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@WithMockUser
class MainPageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CharacterService characterService;

    @Test
    @DisplayName("Before main page appears")
    void openBeforeMain() throws Exception {
        mockMvc.perform(get("/kalandjatek"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Main Page")));
    }

    @Test
    @DisplayName("Main page appears")
    void openMain() throws Exception {
        mockMvc.perform(get("/kalandjatek/fooldal"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Main Page")));
    }
}
