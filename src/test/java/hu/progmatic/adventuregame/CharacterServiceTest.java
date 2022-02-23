package hu.progmatic.adventuregame;

import hu.progmatic.adventuregame.character.*;
import hu.progmatic.adventuregame.inventory.Inventory;
import hu.progmatic.felhasznalo.UserType;
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
        CharacterEntity character = CharacterEntity.builder().name("Name").race(Race.ELF).build();
        CharacterEntity saved = characterService.save(character);
        assertNotNull(saved.getId());
    }

    @Nested
    @DisplayName("One character")
    class CharacterExistsTest {
        private CharacterEntity character;

        @BeforeEach
        void setUp() {
            CharacterEntity newCharacter = CharacterEntity.builder()
                    .name("Name")
                    .maxHp(10)
                    .maxMp(10)
                    .currMp(10)
                    .currHp(10)
                    .gold(10)
                    .imgRef("")
                    .indexImg("")
                    .description("")
                    .inventory(new Inventory())
                    .activeInventory(new Inventory())
                    .attack(7)
                    .defence(8)
                    .race(Race.ELF)
                    .answer(new Answer())
                    .build();
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
            CharacterEntity readed = characterService.getById(character.getId());
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
            List<CharacterEntity> multiple = characterService.findAllByName("Mark Zuckerberg");
            assertThat(multiple)
                    .extracting(CharacterEntity::getName)
                    .contains("Mark Zuckerberg");
        }

        @Test
        @DisplayName("Find all reptilian")
        void findAllByRace() {
            List<CharacterEntity> multiple = characterService.findAllByRace(Race.REPTILIAN);
            assertThat(multiple)
                    .extracting(CharacterEntity::getName)
                    .containsExactlyInAnyOrder("Mark Zuckerberg");
        }

        @Test
        void findAll() {
            List<CharacterEntity> allCharacters = characterService.findAll();
            assertThat(allCharacters)
                    .hasSize(5)
                    .extracting(CharacterEntity::getName)
                    .containsAnyOf("Mark Zuckerberg", "Malfoy", "Bence");
        }
    }
}
