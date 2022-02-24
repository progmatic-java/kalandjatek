package hu.progmatic.adventuregame.character;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
@WithUserDetails("admin")
class CharacterControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CharacterService characterService;


    @Test
    @DisplayName("A Personality Test html megjelenik")
    void personalityTest() throws Exception {
        mockMvc.perform(get("/adventuregame/personalitytest")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Personality Test")));
    }

    @Test
    @DisplayName("Character page megjelenik")
    void characterPage() throws Exception {
        Integer characterId = characterService.getIdByName("Test human");

        mockMvc.perform(
                        get("/adventuregame/characterpage/" + characterId)
                ).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Test human")))
                .andExpect(content().string(containsString("Character page")));
    }
}