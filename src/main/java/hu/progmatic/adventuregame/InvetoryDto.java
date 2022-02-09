package hu.progmatic.adventuregame;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvetoryDto {
  private Integer id;
  private List<ItemDto> items;
}
