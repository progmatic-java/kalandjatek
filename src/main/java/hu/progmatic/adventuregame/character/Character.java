package hu.progmatic.adventuregame.character;

import hu.progmatic.adventuregame.inventory.Inventory;
import hu.progmatic.adventuregame.room.Room;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Character {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String name;
  @Enumerated(EnumType.STRING)
  Race race;
  private String description;
  private Integer hp;
  private Integer mp;
  private Integer gold;
  private String imgRef;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn
  Answer answer;
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn
  Inventory inventory;
  @ManyToOne(cascade = CascadeType.ALL)
  Room charRoom;
}
