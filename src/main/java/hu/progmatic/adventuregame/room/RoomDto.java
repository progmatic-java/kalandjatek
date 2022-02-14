package hu.progmatic.adventuregame.room;

import hu.progmatic.adventuregame.inventory.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDto {
  private Integer id;
  private String roomName;
  private String roomImgRef;
  private String roomAudio;
  private List<ItemDto> items;
  private Map<String, Integer> adjacentRooms;
  private List<String> npcEntities;
}
