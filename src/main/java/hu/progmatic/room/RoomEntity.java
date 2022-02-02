package hu.progmatic.room;

import hu.progmatic.kalandjatek.InventoryEntity;
import hu.progmatic.kalandjatek.character.CharacterEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

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
  @Column(unique = true)
  private String name;
  @OneToOne
  private InventoryEntity inventory;
  @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
  @Builder.Default
  private List<CharacterEntity> characters = new ArrayList<>();
  @OneToMany(mappedBy = "room1", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @Builder.Default
  private List<DoorEntity> doors1 = new ArrayList<>();
  @OneToMany(mappedBy = "room2", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @Builder.Default
  private List<DoorEntity> doors2 = new ArrayList<>();
}
