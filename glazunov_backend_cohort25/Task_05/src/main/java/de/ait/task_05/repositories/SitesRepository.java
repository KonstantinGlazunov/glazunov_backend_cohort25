package de.ait.task_05.repositories;

import de.ait.task_05.models.Site;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SitesRepository extends JpaRepository<Site, Long> {
}
