package hu.progmatic.adventuregame.npc;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class NPCDto implements Serializable {
    private final Integer id;
    private final String name;
    private final String description;
    private final Boolean isItFriendly;
    private Integer hp;
    private Integer mp;
    private Integer gold;
    private final String imgRef;
}
