package hu.progmatic.adventuregame.character;

import hu.progmatic.adventuregame.inventory.Inventory;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class CharacterDto implements Serializable {
    private final Integer id;
    private final String characterName;
    private final Race race;
    private final String description;
    private Integer hp;
    private Integer mp;
    private Integer gold;
    private final String imgRef;
    private final Answer answer;
    private final Integer inventoryId;
}
