package de.ait.mt.repositories;

import de.ait.mt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository <User, Long>{


    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
