package kz.sandibekov.spring.securityjwt.repository;

import kz.sandibekov.spring.securityjwt.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Optional<Role> findByName(String role_user);
}
