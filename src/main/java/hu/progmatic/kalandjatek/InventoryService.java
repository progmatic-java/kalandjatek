package hu.progmatic.kalandjatek;

import hu.progmatic.kalandjatek.room.RoomEntity;
import hu.progmatic.kalandjatek.room.RoomRepository;
import org.springframework.beans.factory.InitializingBean;
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

  public InventoryEntity createInventory(InventoryEntity newInventory) {
    return inventoryRepository.save(newInventory);
  }

  private InvetoryDto buildInventoryDto(InventoryEntity savedEntity) {
    List<ItemDto> itemDtoList = savedEntity.getItems().stream()
        .map(this::buildItemDto)
        .toList();
    return InvetoryDto.builder()
        .id(savedEntity.getId())
        .items(itemDtoList)
        .build();
  }

  public ItemEntity createItem(ItemEntity newItem) {
    return itemRepository.save(newItem);
  }

  public List<ItemEntity> getItemsByInventoryId(Integer inventoryId) {
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

  public List<ItemEntity> createItems(List<ItemEntity> items) {
    return itemRepository.saveAll(items);
  }

  private ItemDto buildItemDto(ItemEntity itemEntity) {
    return ItemDto.builder()
        .id(itemEntity.getId())
        .itemName(itemEntity.getItemName())
        .description(itemEntity.getDescription())
        .typeOfItem(itemEntity.getTypeOfItem())
//        .inventoryId(itemEntity.getInventory().getId())
        .build();
  }

  public void deleteAllItems() {
    itemRepository.deleteAll();
  }

  public void addItemToInventory(Integer inventoryId, Integer itemId) {
    ItemEntity item = itemRepository.getById(itemId);
    InventoryEntity inventory = inventoryRepository.getById(inventoryId);
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
    InventoryEntity inventory = inventoryRepository.getById(inventoryId);
    ItemEntity item = itemRepository.getById(itemId);
    inventory.getItems().remove(item);
    item.setInventory(null);
  }
}
