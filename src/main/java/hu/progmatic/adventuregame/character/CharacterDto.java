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
    private  Integer id;
    private  String characterName;
    private  Race race;
    private  String description;
    private Integer hp;
    private Integer mp;
    private Integer gold;
    private Integer attack;
    private Integer defence;
    private  String imgRef;
    private  Answer answer;
    private List<ItemDto> items;
}
