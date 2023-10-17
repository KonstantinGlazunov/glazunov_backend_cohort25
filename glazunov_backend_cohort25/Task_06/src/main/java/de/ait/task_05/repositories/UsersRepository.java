package de.ait.task_05.repositories;

import de.ait.task_05.models.Event;
import de.ait.task_05.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface UsersRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    Set<User> findAllByEventsContainsOrderById(Event event);

    Optional<User> findByEmail(String email);
}
