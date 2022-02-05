package hu.progmatic.kalandjatek.character;

import hu.progmatic.kalandjatek.InventoryEntity;
import hu.progmatic.kalandjatek.room.RoomEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CharacterEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer id;
  String name;
  @Enumerated(EnumType.STRING)
  Race race;
  String description;
  Integer hp;
  Integer mp;
  Integer gold;
  String imgRef;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn
  Answer answer;
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn
  InventoryEntity inventory;
  @ManyToOne(cascade = CascadeType.ALL)
  RoomEntity charRoom;
}
