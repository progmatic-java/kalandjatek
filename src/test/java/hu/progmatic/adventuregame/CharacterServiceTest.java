package hu.progmatic.adventuregame;

import hu.progmatic.felhasznalo.UserType;
import hu.progmatic.adventuregame.character.CharacterDto;
import hu.progmatic.adventuregame.character.Character;
import hu.progmatic.adventuregame.character.CharacterService;
import hu.progmatic.adventuregame.character.Race;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CharacterServiceTest {

    @Autowired
    private CharacterService characterService;

    @Test
    @DisplayName("Create character")
    void createCharacter() {
        Character character = Character.builder().name("Name").race(Race.ELF).build();
        Character saved = characterService.save(character);
        assertNotNull(saved.getId());
    }

    @Nested
    @DisplayName("One character")
    class CharacterExistsTest {
        private Character character;

        @BeforeEach
        void setUp() {
            Character newCharacter = Character.builder().name("Name").race(Race.ELF).build();
            character = characterService.save(newCharacter);
        }

        @Test
        @DisplayName("Get character by id")
        void getCharacter() {
            CharacterDto readed = characterService.getCharacterDtoById(character.getId());
            assertNotNull(readed.getId());
            assertEquals("Name", readed.getCharacterName());
            assertEquals(Race.ELF, readed.getRace());
        }

        @Test
        @DisplayName("Update a character")
        void updateCharacter() {
            character.setName("New name");
            characterService.save(character);
            CharacterDto updated = characterService.getCharacterDtoById(character.getId());
            assertEquals("New name", updated.getCharacterName());
        }

        @Test
        @DisplayName("Delete a character")
        @WithMockUser(roles = UserType.Roles.USER_WRITE_ROLE)
        void deleteCharacter() {
            Character readed = characterService.getById(character.getId());
            assertNotNull(readed.getId());
            characterService.delete(character.getId());
            Exception exception = null;
            try {
                characterService.getCharacterDtoById(character.getId());
            } catch (Exception e) {
                exception = e;
            }
            assertNotNull(exception);
        }
    }

    @Nested
    @DisplayName("Multiple character")
    class MultipleCharacterExistsTest {
        @Test
        @DisplayName("Find Mark Zuckerberg")
        void findAllByName() {
            List<Character> multiple = characterService.findAllByName("Mark Zuckerberg");
            assertThat(multiple)
                    .extracting(Character::getName)
                    .contains("Mark Zuckerberg");
        }

        @Test
        @DisplayName("Find all reptilian")
        void findAllByRace() {
            List<Character> multiple = characterService.findAllByRace(Race.REPTILIAN);
            assertThat(multiple)
                    .extracting(Character::getName)
                    .containsExactlyInAnyOrder("Mark Zuckerberg");
        }

        @Test
        void findAll() {
            List<Character> allCharacters = characterService.findAll();
            assertThat(allCharacters)
                    .hasSize(5)
                    .extracting(Character::getName)
                    .containsAnyOf("Mark Zuckerberg", "Malfoy", "Bence");
        }
    }
}
