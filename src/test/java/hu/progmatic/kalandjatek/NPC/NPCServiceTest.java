package hu.progmatic.kalandjatek.NPC;

import hu.progmatic.felhasznalo.UserType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class NPCServiceTest {

    @Autowired
    private NPCService npcService;

    @Test
    @DisplayName("Create NPC")
    void createNpc() {
        NPCEntity npc = NPCEntity.builder().name("Burrows").description("The drunk owner of The Black Hole Inn").build();
        NPCEntity saved = npcService.save(npc);
        assertNotNull(saved.getId());
    }

    @Nested
    @DisplayName("One NPC")
    class NpcExistsTest {
        private NPCEntity npc;

        @BeforeEach
        void setUp() {
            NPCEntity newNpc = NPCEntity.builder().name("Burrows").description("The drunk owner of The Black Hole Inn").build();
            npc = npcService.save(newNpc);
        }

        @Test
        @DisplayName("Get NPC by id")
        void getNpc() {
            NPCDto readed = npcService.getNPCDtoById(npc.getId());
            assertNotNull(readed.getId());
            assertEquals("Burrows", readed.getName());
            assertEquals("The drunk owner of The Black Hole Inn", readed.getDescription());
        }

        @Test
        @DisplayName("Update a NPC")
        void updateCharacter() {
            npc.setName("Burrows the Saint");
            npcService.save(npc);
            NPCDto updated = npcService.getNPCDtoById(npc.getId());
            assertEquals("Burrows the Saint", updated.getName());
        }

        @Test
        @DisplayName("Delete an NPC")
        @WithMockUser(roles = UserType.Roles.USER_WRITE_ROLE)
        void deleteCharacter() {
            NPCEntity readed = npcService.getById(npc.getId());
            assertNotNull(readed.getId());
            npcService.delete(npc.getId());
            Exception exception = null;
            try {
                npcService.getNPCDtoById(npc.getId());
            } catch (Exception e) {
                exception = e;
            }
            assertNotNull(exception);
        }
    }

    @Nested
    @DisplayName("Multiple character")
    class MultipleNPCExistsTest {
        @Test
        @DisplayName("Find Burrows")
        void findAllByName() {
            List<NPCEntity> multiple = npcService.findAllByName("Burrows");
            assertThat(multiple)
                    .extracting(NPCEntity::getName)
                    .contains("Burrows");
        }

        @Test
        @DisplayName("Find all friendly")
        void findAllByRace() {
            List<NPCEntity> multiple = npcService.findAllByIsItFriendly(true);
            assertThat(multiple)
                    .extracting(NPCEntity::getName)
                    .containsExactlyInAnyOrder("Switcher", "Burrows");
        }

        @Test
        @Disabled
        void findAll() {
            List<NPCEntity> allCharacters = npcService.findAll();
            assertThat(allCharacters)
                    .hasSize(3)
                    .extracting(NPCEntity::getName)
                    .containsAnyOf("Switcher", "Burrows", "Lady Regex");
        }
    }
}