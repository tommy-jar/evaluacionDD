package pe.upc.evaluaciondd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.upc.evaluaciondd.entity.Player;

public interface RepositoryPlayer extends JpaRepository<Player, Long > {

}
