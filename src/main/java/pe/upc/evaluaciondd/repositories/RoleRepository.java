package pe.upc.evaluaciondd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.upc.evaluaciondd.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
