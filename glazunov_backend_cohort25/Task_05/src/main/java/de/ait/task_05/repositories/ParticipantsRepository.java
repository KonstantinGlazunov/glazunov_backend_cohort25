package de.ait.task_05.repositories;

import de.ait.task_05.models.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantsRepository extends JpaRepository<Participant, Long> {
    boolean existsByEmail(String email);
}
