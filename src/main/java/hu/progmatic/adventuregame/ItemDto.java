package hu.progmatic.adventuregame;

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
  private String description;
  private ItemEnum typeOfItem;
  private Integer inventoryId;
}
