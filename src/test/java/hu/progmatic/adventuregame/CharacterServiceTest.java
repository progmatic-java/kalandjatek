package hu.progmatic.adventuregame;

import hu.progmatic.adventuregame.character.*;
import hu.progmatic.adventuregame.inventory.Inventory;
import hu.progmatic.adventuregame.inventory.Item;
import hu.progmatic.felhasznalo.UserType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import javax.persistence.criteria.CriteriaBuilder;
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
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("One character")
    class CharacterExistsTest {
        private CharacterEntity character ;

        @BeforeEach
        void setUp() {
            Inventory inv = new Inventory();
            inv.getItems().add(Item.builder().itemName("Mace").build());

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
                    .activeInventory(inv)
                    .attack(7)
                    .defence(8)
                    .race(Race.ELF)
                    .build();
            character = characterService.save(newCharacter);
        }

        @Test
        @DisplayName("Get character by id")
        @Order(1)
        void getCharacter() {
            CharacterDto readed = characterService.getCharacterDtoById(1);
            assertNotNull(readed.getId());
            assertEquals("Test elf", readed.getCharacterName());
            assertEquals(Race.ELF, readed.getRace());
        }

        @Test
        @DisplayName("Update a character")
        @Order(2)
        void updateCharacter() {
            characterService.getCharacterDtoById(1).setCharacterName("NEMTOM");
            characterService.save(character);
            CharacterDto updated = characterService.getCharacterDtoById(1);
            assertEquals("Test elf", updated.getCharacterName());
        }

        @Test
        @DisplayName("Delete a character")
        @Order(3)
        @WithMockUser(roles = UserType.Roles.USER_WRITE_ROLE)
        void deleteCharacter() {
            CharacterEntity readed = characterService.getById(1);
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
        @DisplayName("Find Test orc")
        void findAllByName() {
            List<CharacterEntity> multiple = characterService.findAllByName("Test orc");
            assertThat(multiple)
                    .extracting(CharacterEntity::getName)
                    .contains("Test orc");
        }

        @Test
        @DisplayName("Find all reptilian")
        void findAllByRace() {
            List<CharacterEntity> multiple = characterService.findAllByRace(Race.REPTILIAN);
            assertThat(multiple)
                    .extracting(CharacterEntity::getName)
                    .containsExactlyInAnyOrder("Test reptilian");
        }

        @Test
        void findAll() {
            List<CharacterEntity> allCharacters = characterService.findAll();
            assertThat(allCharacters)
                    .hasSize(5)
                    .extracting(CharacterEntity::getName)
                    .containsAnyOf("Test reptilian", "Test orc", "Test elf");
        }
    }

    @Test
    void getResultCharacter() {
        CharacterDto created = characterService.getResultCharacter(
            Answer.builder()
                .name("Test character")
                .race1(Race.ELF)
                .race2(Race.ELF)
                .race3(Race.ELF)
                .race4(Race.ELF)
                .race5(Race.ELF)
                .race6(Race.ELF)
                .race7(Race.ELF)
                .race8(Race.ELF)
            .build()
        );

        CharacterDto readed = characterService.getCharacterDtoById(created.getId());
        assertThat(readed.getActiveWeapon()).isNotNull();
        characterService.delete(readed.getId());
    }
}
