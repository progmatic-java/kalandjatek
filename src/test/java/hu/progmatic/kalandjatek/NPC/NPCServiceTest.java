package hu.progmatic.kalandjatek.NPC;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


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
        NPCEntity newEntity = NPCEntity.builder().name("Runner").description("The coffee lover of The Black Hole Inn").build();
        npcService.save(newEntity);
        assertNotNull(newEntity.getId());
    }

    @Test
    @DisplayName("Delete an NPC")
    void deleteCharacter() {
        NPCEntity newEntity = NPCEntity.builder().name("Zs Black").description("Phantom of Progmatchique").build();
        npcService.save(newEntity);
        assertNotNull(newEntity.getId());
        npcService.delete(newEntity.getId());
        Exception exception = null;
        try {
            npcService.getNPCDtoById(newEntity.getId());
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
    }


    @Nested
    @DisplayName("One NPC")
    class NpcExistsTest {
        private NPCEntity npc;

        @BeforeEach
        void setUp() {
            NPCEntity newNpc = NPCEntity.builder().name("Maya").description("The waitress of The Black Hole Inn").build();
            npc = npcService.save(newNpc);
        }

        @AfterEach
        void tearDown() {
            npcService.delete(npc.getId());
        }

        @Test
        @DisplayName("Get NPC by id")
        void getNpc() {
            NPCDto readed = npcService.getNPCDtoById(npc.getId());
            assertNotNull(readed.getId());
            assertEquals("Maya", readed.getName());
            assertEquals("The waitress of The Black Hole Inn", readed.getDescription());
        }

        @Test
        @DisplayName("Update a NPC")
        void updateCharacter() {
            npc.setName("Burrows the Saint");
            npcService.save(npc);
            NPCDto updated = npcService.getNPCDtoById(npc.getId());
            assertEquals("Burrows the Saint", updated.getName());
        }

    }

    @Nested
    @DisplayName("Multiple character")
    class MultipleNPCExistsTest {
        @Test
        @DisplayName("Find Burrows")
        void findAllByName() {
            NPCEntity burrow = npcService.findByName("Burrows");
            assertNotNull(burrow.getId());
            assertEquals("Burrows", burrow.getName());

        }

        @Test
        @DisplayName("Find all friendly")
        void findAllByRace() {
            List<NPCEntity> multiple = npcService.findAllByFriendly(true);
            assertThat(multiple)
                    .extracting(NPCEntity::getName)
                    .containsExactlyInAnyOrder("Switcher", "Burrows");
        }

        @Test
        void findAll() {
            List<NPCEntity> allCharacters = npcService.findAll();
            assertThat(allCharacters)
                    .hasSize(4)
                    .extracting(NPCEntity::getName)
                    .containsAnyOf("Switcher", "Burrows", "Lady Regex");
        }
    }
}