package hu.progmatic.adventuregame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class InventoryService{

  @Autowired
  private InventoryRepository inventoryRepository; //parent
  @Autowired
  private ItemRepository itemRepository; // child

  public Inventory createInventory(Inventory newInventory) {
    return inventoryRepository.save(newInventory);
  }

  private InvetoryDto buildInventoryDto(Inventory savedEntity) {
    List<ItemDto> itemDtoList = savedEntity.getItems().stream()
        .map(this::buildItemDto)
        .toList();
    return InvetoryDto.builder()
        .id(savedEntity.getId())
        .items(itemDtoList)
        .build();
  }

  public Item createItem(Item newItem) {
    return itemRepository.save(newItem);
  }

  public List<Item> getItemsByInventoryId(Integer inventoryId) {
    return inventoryRepository.getById(inventoryId).getItems().stream().toList();
  }

  public void deleteById(Integer itemId) {
    itemRepository.deleteById(itemId);
  }

  public void deleteInventory(Integer inventoryId) {
    inventoryRepository.deleteById(inventoryId);
  }

  public boolean itemExistsById(Integer id) {
    return itemRepository.existsById(id);
  }

  public List<Item> createItems(List<Item> items) {
    return itemRepository.saveAll(items);
  }

  private ItemDto buildItemDto(Item item) {
    return ItemDto.builder()
        .id(item.getId())
        .itemName(item.getItemName())
        .description(item.getDescription())
        .typeOfItem(item.getTypeOfItem())
//        .inventoryId(itemEntity.getInventory().getId())
        .build();
  }

  public void deleteAllItems() {
    itemRepository.deleteAll();
  }

  public void addItemToInventory(Integer inventoryId, Integer itemId) {
    Item item = itemRepository.getById(itemId);
    Inventory inventory = inventoryRepository.getById(inventoryId);
    item.setInventory(inventory);
    inventory.getItems().add(item);
  }

  public InvetoryDto getInventoryById(Integer id) {
    return buildInventoryDto(inventoryRepository.getById(id));
  }

  public ItemDto getItemById(Integer id) {
    return buildItemDto(itemRepository.getById(id));
  }

  public void deleteItemFromInvetory(Integer inventoryId, Integer itemId) {
    Inventory inventory = inventoryRepository.getById(inventoryId);
    Item item = itemRepository.getById(itemId);
    inventory.getItems().remove(item);
    item.setInventory(null);
  }
}
