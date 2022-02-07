package hu.progmatic.kalandjatek.room;

import hu.progmatic.kalandjatek.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDto {
  private Integer id;
  private String roomName;
  private List<String> items;
  private List<String> adjacentRooms;
  private List<String> npcEntities;
}
