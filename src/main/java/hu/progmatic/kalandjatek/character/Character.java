package hu.progmatic.kalandjatek.character;

import hu.progmatic.kalandjatek.Inventory;
import hu.progmatic.kalandjatek.room.Room;
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
