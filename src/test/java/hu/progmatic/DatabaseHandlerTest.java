package hu.progmatic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DatabaseHandlerTest {
    DatabaseHandler jatek;

    @BeforeEach
    void setUp() {
        jatek = new DatabaseHandler();
    }

    @Test
    @DisplayName("5 db szoba van a térképen")
    void roomCount() {
        assertEquals(5, jatek.getRoomCount());
    }

    @Test
    @DisplayName("Ha két szoba között létrehozunk egy ajtót, akkor a szobák közt át tudunk menni")
    void addDoor() {
        assertThat(jatek.getRoomsNextDoor("Bolt")).hasSize(0);
        jatek.addDoor("Bolt", "Főtér");
        assertThat(jatek.getRoomsNextDoor("Bolt"))
                .hasSize(1)
                .containsExactly("Főtér");
    }

    @Nested
    @DisplayName("Kész térképpel")
    class FullMapTest {
        @BeforeEach
        void setUp() {
            jatek.addDoor("Főtér", "Bolt");
            jatek.addDoor("Főtér", "Kocsma");
            jatek.addDoor("Főtér", "Templom");
            jatek.addDoor("Kocsma", "Pince");
        }

        @Test
        void roomsNextDoor() {
            assertThat(jatek.getRoomsNextDoor("Főtér"))
                    .hasSize(3)
                    .containsExactlyInAnyOrder("Bolt", "Kocsma", "Templom");

            assertThat(jatek.getRoomsNextDoor("Kocsma"))
                    .hasSize(2)
                    .containsExactlyInAnyOrder("Főtér", "Pince");
        }

        @Test
        void targyLetrehozasa() {
            jatek.createItem("Balta", "Kocsma");
            assertThat(jatek.getItems("Kocsma"))
                    .hasSize(1)
                    .containsExactlyInAnyOrder("Balta");
            jatek.createItem("Sör", "Kocsma");
            assertThat(jatek.getItems("Kocsma"))
                    .hasSize(2)
                    .containsExactlyInAnyOrder("Balta", "Sör");

        }
    }
}