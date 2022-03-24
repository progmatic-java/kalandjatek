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
        CharacterDto character = characterService.getResultCharacter(
            Answer.builder()
                .name("Test character")
                .race1(Race.HUMAN)
                .race2(Race.HUMAN)
                .race3(Race.HUMAN)
                .race4(Race.HUMAN)
                .race5(Race.HUMAN)
                .race6(Race.HUMAN)
                .race7(Race.HUMAN)
                .race8(Race.HUMAN)
                .build());

        mockMvc.perform(
                        get("/adventuregame/characterpage/" + character.getId())
                ).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Test character")))
                .andExpect(content().string(containsString("Character page")));

        characterService.delete(character.getId());
    }
}