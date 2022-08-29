package ist.challange.adeSalahuddin.repository;

import ist.challange.adeSalahuddin.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
