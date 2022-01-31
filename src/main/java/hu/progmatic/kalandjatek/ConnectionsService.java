package hu.progmatic.kalandjatek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ConnectionsService {

    @Autowired
    private InventoryRepository inventoryRepository; //parent
    @Autowired
    private ItemRepository itemRepository; // child

    public InventoryEntity createInventory(InventoryEntity newInventory) {
        return inventoryRepository.save(newInventory);
    }

    public ItemsEntity createItems(ItemsEntity newItem) {
        return itemRepository.save(newItem);
    }

    public String getItemNameById(Integer id) {
        return itemRepository.getById(id).getItemName();
    }

    public List<ItemsEntity> getItemsByInventoryId(Integer inventoryId) {
        return inventoryRepository.getById(inventoryId).getItems().stream().toList();
    }

    public void deleteItem(ItemsEntity item) {
        itemRepository.delete(item);
    }

    public void deleteInventory(InventoryEntity inventory) {
        inventoryRepository.delete(inventory);
    }

    public boolean itemExistsById(Integer id) {
        return itemRepository.existsById(id);
    }

}
