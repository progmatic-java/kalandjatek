package hu.progmatic.adventuregame.npc;

import hu.progmatic.adventuregame.inventory.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NPCDto implements Serializable {
    private Integer id;
    private String name;
    private String description;
    private Boolean friendly;
    private Integer hp;
    private Integer mp;
    private Integer gold;
    private String imgRef;
    private Integer inventoryId;
    private List<ItemDto> items;
    private ActionCommand firstAction;
}
