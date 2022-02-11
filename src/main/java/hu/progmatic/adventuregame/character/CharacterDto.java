package hu.progmatic.adventuregame.character;

import hu.progmatic.adventuregame.inventory.ItemDto;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class CharacterDto implements Serializable {
    private  Integer id;
    private  String characterName;
    private  Race race;
    private  String description;
    private Integer hp;
    private Integer mp;
    private Integer gold;
    private  String imgRef;
    private  Answer answer;
    private  Integer inventoryId;
    private List<ItemDto> items;
}
