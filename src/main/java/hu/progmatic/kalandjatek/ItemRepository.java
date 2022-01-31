package hu.progmatic.kalandjatek;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemsEntity, Integer> {
}
