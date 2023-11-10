package kz.sandibekov.spring.securityjwt.repository;

import kz.sandibekov.spring.securityjwt.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
