package hu.progmatic.kalandjatek;

import hu.progmatic.kalandjatek.character.CharacterDto;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class InventoryService implements InitializingBean {

    @Autowired
    private InventoryRepository inventoryRepository; //parent
    @Autowired
    private ItemRepository itemRepository; // child

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public InvetoryDto createInventory(InventoryEntity newInventory) {
        InventoryEntity savedEntity = inventoryRepository.save(newInventory);
        return buildInventoryDto(savedEntity);
    }

    private InvetoryDto buildInventoryDto(InventoryEntity savedEntity) {
        Map<Integer, String> itemsIdNameMap = getItemsIdNameMap(savedEntity.getItems());
        return InvetoryDto.builder()
            .id(savedEntity.getId())
            .itemIdAndNameMap(itemsIdNameMap)
            .build();
    }

    private Map<Integer, String> getItemsIdNameMap(List<ItemEntity> items) {
        Map<Integer, String> itemsIdNameMap = new HashMap<>();
        for (ItemEntity item : items) {
            itemsIdNameMap.put(item.getId(), item.getItemName());
        }
        return itemsIdNameMap;
    }

    public ItemDto createItem(ItemEntity newItem) {
        return buildItemDto(itemRepository.save(newItem));
    }

    public String getItemNameById(Integer id) {
        return itemRepository.getById(id).getItemName();
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

    public List<ItemDto> createItems(List<ItemEntity> items) {
        List<ItemEntity> itemEntities = itemRepository.saveAll(items);
        return itemEntities.stream().map(this::buildItemDto).toList();
    }

    private ItemDto buildItemDto(ItemEntity itemEntity) {
        return ItemDto.builder()
            .id(itemEntity.getId())
            .itemName(itemEntity.getItemName())
            .description(itemEntity.getDescription())
            .typeOfItem(itemEntity.getTypeOfItem())
//            .inventoryId(itemEntity.getInventory().getId())
            .build();
    }
}
