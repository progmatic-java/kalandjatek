package hu.progmatic.kalandjatek.character;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class CharacterDto implements Serializable {
    private final Integer id;
    private final String characterName;
    private final Race race;
    private final List<InventoryDto> inventory;

    @Data
    @Builder
    public static class InventoryDto implements Serializable {
        private final Integer id;
        private final String inventoryName;
    }

}
