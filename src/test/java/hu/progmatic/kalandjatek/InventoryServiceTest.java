package hu.progmatic.kalandjatek;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InventoryServiceTest {

    @Autowired
    private InventoryService inventoryService;

    @Test
    @Disabled
    void createInventory() {
        InventoryEntity newInventory = InventoryEntity.builder()
                .build();
        InvetoryDto createdInventory = inventoryService.createInventory(newInventory);
        inventoryService.createItem(ItemEntity.builder()
                .itemName("Iron Sword")
                .description("The mightyest sword in the land")
                .typeOfItem(ItemEnum.ATTACK)
//                .inventory(createdInventory)
                .build());
        inventoryService.createItem(ItemEntity.builder()
                .itemName("Wooden Shield")
                .description("Its made out of wood")
                .typeOfItem(ItemEnum.SHIELD)
//                .inventory(createdInventory)
                .build());
        assertNotNull(createdInventory.getId());
        assertThat(inventoryService.getItemsByInventoryId(createdInventory.getId()))
                .extracting(ItemEntity::getItemName)
                .containsExactlyInAnyOrder("Iron Sword", "Wooden Shield");

    }

    @Test
    void createItem() {
        ItemEntity newItem = ItemEntity.builder()
                .itemName("Beer")
                .description("A good old ale from Gödör, it always comes handy")
                .typeOfItem(ItemEnum.CONSUMABLE)
                .build();
        ItemDto createdItem = inventoryService.createItem(newItem);
        assertNotNull(createdItem.getId());
        assertEquals("Beer", inventoryService.getItemNameById(newItem.getId()));
    }

    @Test
    void createMultipleItems() {
        List<ItemDto> items = inventoryService.createItems(
            List.of(
                ItemEntity.builder()
                    .itemName("Beer")
                    .description("Good cold beer")
                    .typeOfItem(ItemEnum.CONSUMABLE)
                    .build(),
                ItemEntity.builder()
                    .itemName("Sword")
                    .description("Mighty sword")
                    .typeOfItem(ItemEnum.ATTACK)
                    .build(),
                ItemEntity.builder()
                    .itemName("Shield")
                    .description("Strong AF")
                    .typeOfItem(ItemEnum.SHIELD)
                    .build(),
                ItemEntity.builder()
                    .itemName("Helmet")
                    .description("Anti bonk item")
                    .typeOfItem(ItemEnum.SHIELD)
                    .build(),
                ItemEntity.builder()
                    .itemName("Cheese")
                    .description("Tasty and stinky")
                    .typeOfItem(ItemEnum.CONSUMABLE)
                    .build()
            )
        );
        assertThat(items)
            .hasSize(5)
            .extracting(ItemDto::getItemName)
            .containsExactlyInAnyOrder("Cheese", "Helmet", "Shield", "Sword", "Beer");
    }

    @Nested
    class DeleteTest {
        InvetoryDto inventory;
        ItemDto item;

        @BeforeEach
        void setUp() {
            inventory = inventoryService.createInventory(InventoryEntity.builder()
                    .build());

            item = inventoryService.createItem(ItemEntity.builder()
                    .itemName("Beer")
                    .description("A good old ale from Gödör, it always comes handy")
                    .typeOfItem(ItemEnum.CONSUMABLE)
                    .build());
        }

        @AfterEach
        void tearDown() {
                inventoryService.deleteById(item.getId());
                inventoryService.deleteInventory(inventory.getId());
        }

        @Test
        void deleteItem() {
            inventoryService.deleteById(item.getId());
            assertFalse(inventoryService.itemExistsById(item.getId()));
        }
    }
}