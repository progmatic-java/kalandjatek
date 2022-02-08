package hu.progmatic.kalandjatek;

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
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Builder.Default
    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();
}
