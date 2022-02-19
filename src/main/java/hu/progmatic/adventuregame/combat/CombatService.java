package hu.progmatic.adventuregame.combat;

import hu.progmatic.adventuregame.character.CharacterDto;
import hu.progmatic.adventuregame.character.CharacterEntity;
import hu.progmatic.adventuregame.character.CharacterRepository;
import hu.progmatic.adventuregame.inventory.Inventory;
import hu.progmatic.adventuregame.inventory.InventoryService;
import hu.progmatic.adventuregame.inventory.Item;
import hu.progmatic.adventuregame.inventory.ItemEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class CombatService {

    @Autowired
    private CharacterRepository characterRepository;

    public void swapActiveItem(Integer characterId, Integer itemId) {
        CharacterEntity character = characterRepository.getById(characterId);
        Item item = character.getInventory().getItems().stream().filter(item1 -> item1.getId().equals(itemId)).findAny().orElseThrow();
        Item activeItem = character.getActiveInventory().getItems().stream().filter(item1 -> item1.getTypeOfItem().equals(item.getTypeOfItem())).findAny().orElseThrow();
        character.getInventory().getItems().remove(item);
        character.getActiveInventory().getItems().remove(activeItem);
        character.getInventory().getItems().add(activeItem);
        character.getActiveInventory().getItems().add(item);
        item.setInventory(character.getActiveInventory());
        activeItem.setInventory(character.getInventory());
    }

}
