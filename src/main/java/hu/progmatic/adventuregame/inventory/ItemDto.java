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
  private Integer defence;
  private Integer damage;

  public Item buildEntity() {
    return Item.builder()
        .itemName(itemName)
        .value(value)
        .description(description)
        .typeOfItem(typeOfItem)
        .hp(hp)
        .mp(mp)
        .attack(attack)
        .defence(defence)
        .damage(damage)
        .build();
  }
}
