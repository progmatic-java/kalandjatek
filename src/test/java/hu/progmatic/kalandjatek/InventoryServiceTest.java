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
    void createInventory() {
        InventoryEntity inventory = inventoryService.createInventory(InventoryEntity.builder().build());
        assertThat(inventory.getId()).isNotNull();
    }

    @Test
    void createItem() {
        ItemEntity newItem = ItemEntity.builder()
                .itemName("Beer")
                .description("A good old ale from Gödör, it always comes handy")
                .typeOfItem(ItemEnum.CONSUMABLE)
                .build();
        ItemEntity createdItem = inventoryService.createItem(newItem);
        assertNotNull(createdItem.getId());
        assertEquals("Beer", createdItem.getItemName());
    }

    @Test
    void createMultipleItems() {
        List<ItemEntity> items = inventoryService.createItems(
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
            .extracting(ItemEntity::getItemName)
            .containsExactlyInAnyOrder("Cheese", "Helmet", "Shield", "Sword", "Beer");
    }

    @Test
    void getItemById() {
        ItemEntity item = inventoryService.createItem(ItemEntity.builder().itemName("Potion").build());
        ItemDto dto = inventoryService.getItemById(item.getId());
        assertEquals("Potion", dto.getItemName());
        assertNotNull(dto.getId());
    }

    @Nested
    class InventoryItemTest {
        List<ItemEntity> itemList;
        InventoryEntity inventory;

        @BeforeEach
        void setUp() {
            inventory = inventoryService.createInventory(InventoryEntity.builder().build());
            itemList = inventoryService.createItems(
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
        }

        @AfterEach
        void tearDown() {
            inventoryService.deleteAllItems();
            inventoryService.deleteInventory(inventory.getId());
        }

        @Test
        void addItemToInventory() {
            ItemEntity sword = itemList.stream().filter(item -> item.getItemName().equals("Sword")).findAny().orElseThrow();
            inventoryService.addItemToInventory(inventory.getId(), sword.getId());
            InvetoryDto dto = inventoryService.getInventoryById(inventory.getId());
            assertThat(dto.getItems())
                .hasSize(1)
                .extracting(ItemDto::getItemName)
                .containsExactlyInAnyOrder("Sword");
        }

        @Test
        void deleteItemFromInventory() {
            ItemEntity beer = itemList.stream().filter(item -> item.getItemName().equals("Beer")).findAny().orElseThrow();
            inventoryService.addItemToInventory(inventory.getId(), beer.getId());
            InvetoryDto dto = inventoryService.getInventoryById(inventory.getId());
            assertThat(dto.getItems())
                .hasSize(1)
                .extracting(ItemDto::getItemName)
                .containsExactlyInAnyOrder("Beer");

            inventoryService.deleteItemFromInvetory(inventory.getId(), beer.getId());
            dto = inventoryService.getInventoryById(inventory.getId());
            assertThat(dto.getItems())
                .hasSize(0);
        }
    }
}