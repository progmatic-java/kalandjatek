package hu.progmatic.kalandjatek;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ConnectionsServiceTest {

    @Autowired
    private ConnectionsService service;

    @Test
    void createInventory() {
        InventoryEntity newInventory = InventoryEntity.builder()
                .build();
        InventoryEntity createdInventory = service.createInventory(newInventory);
        service.createItems(ItemsEntity.builder()
                .itemName("Iron Sword")
                .description("The mightyest sword in the land")
                .typeOfItem(ItemsEnum.ATTACK)
                .inventory(createdInventory)
                .build());
        service.createItems(ItemsEntity.builder()
                .itemName("Wooden Shield")
                .description("Its made out of wood")
                .typeOfItem(ItemsEnum.SHIELD)
                .inventory(createdInventory)
                .build());
        assertNotNull(createdInventory.getId());
        assertThat(service.getItemsByInventoryId(createdInventory.getId()))
                .extracting(ItemsEntity::getItemName)
                .containsExactlyInAnyOrder("Iron Sword", "Wooden Shield");

    }

    @Test
    void createItem() {
        ItemsEntity newItem = ItemsEntity.builder()
                .itemName("Beer")
                .description("A good old ale from Gödör, it always comes handy")
                .typeOfItem(ItemsEnum.CONSUMABLE)
                .build();
        ItemsEntity createdItem = service.createItems(newItem);
        assertNotNull(createdItem.getId());
        assertEquals("Beer", service.getItemNameById(newItem.getId()));

    }

    @Nested
    class DeleteTest {
        InventoryEntity inventory;
        ItemsEntity item;
        boolean shouldDeleteInTearDown = true;

        @BeforeEach
        void setUp() {
            inventory = service.createInventory(InventoryEntity.builder()
                    .build());

            item = service.createItems(ItemsEntity.builder()
                    .itemName("Beer")
                    .description("A good old ale from Gödör, it always comes handy")
                    .typeOfItem(ItemsEnum.CONSUMABLE)
                    .build());


        }

        @AfterEach
        void tearDown() {
            if (shouldDeleteInTearDown) {
                service.deleteItem(item);
                service.deleteInventory(inventory);
            }
        }

        @Test
        void deleteItem() {
            service.deleteItem(item);
            assertFalse(service.itemExistsById(item.getId()));
            shouldDeleteInTearDown = false;
        }
    }
}