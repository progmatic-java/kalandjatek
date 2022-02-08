package hu.progmatic.kalandjatek.room;

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
  private List<String> items;
  private Map<String, Integer> adjacentRooms;
  private List<String> npcEntities;
}
