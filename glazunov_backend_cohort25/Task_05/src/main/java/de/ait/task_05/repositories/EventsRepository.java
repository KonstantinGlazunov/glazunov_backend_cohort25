package de.ait.task_05.repositories;

import de.ait.task_05.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface EventsRepository extends JpaRepository<Event, Long> {


    @Modifying
    @Query("UPDATE Event e SET e.site.id = :newSiteId WHERE e.id = :eventId")
    @Transactional
    void upDateSiteId(@Param("eventId") Long eventId, @Param("newSiteId") Long newSiteId);

    Set<Event> findEventsBySiteId(Long siteId);

}
