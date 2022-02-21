package hu.progmatic.adventuregame.character;

import hu.progmatic.adventuregame.inventory.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CharacterDto implements Serializable {
    private Integer id;
    private String characterName;
    private Race race;
    private String description;
    private Integer maxHp;
    private Integer currHp;
    private Integer maxMp;
    private Integer currMp;
    private Integer gold;
    private Integer attack;
    private Integer defence;
    private String imgRef;
    private Answer answer;
    private List<ItemDto> allItems;
    private List<ItemDto> consumableItems;
    private List<ItemDto> combatItems;
    private ItemDto activeWeapon;
    private ItemDto activeShield;
}
