package hu.progmatic.room;

import hu.progmatic.kalandjatek.InventoryEntity;
import hu.progmatic.kalandjatek.character.CharacterEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RoomEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @NotEmpty
  String name;
  @OneToOne
  InventoryEntity inventory;
  @OneToOne
  CharacterEntity character;
  @ManyToMany(cascade = CascadeType.ALL)
  @Builder.Default
  List<DoorEntity> door = new ArrayList<>();
}
