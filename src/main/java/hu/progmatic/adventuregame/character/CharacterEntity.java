package hu.progmatic.adventuregame.character;

import hu.progmatic.adventuregame.inventory.Inventory;
import hu.progmatic.adventuregame.npc.NPC;
import hu.progmatic.adventuregame.room.Room;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CharacterEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String name;
  @Enumerated(EnumType.STRING)
  private Race race;
  private String description;
  private Integer maxHp;
  private Integer maxMp;
  private Integer currMp;
  private Integer currHp;
  private Integer gold;
  private Integer attack;
  private Integer defence;
  private String imgRef;
  private String indexImg;
  @Builder.Default
  private Integer latestRoomId = null;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn
  private Answer answer;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn
  @Builder.Default
  private Inventory inventory = new Inventory();

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn
  @Builder.Default
  private Inventory activeInventory = new Inventory();

  @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  private List<Room> playerRooms = new ArrayList<>();
}
