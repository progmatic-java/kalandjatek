package hu.progmatic.adventuregame.inventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDto {
  private Integer id;
  private String itemName;
  private Integer value;
  private String description;
  private ItemEnum typeOfItem;
  private Integer inventoryId;
  private Integer hp;
  private Integer mp;
  private Integer attack;
  private Integer shield;
  private Integer damage;
}
