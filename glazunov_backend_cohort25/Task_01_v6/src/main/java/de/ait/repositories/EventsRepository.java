package de.ait.repositories;

import de.ait.models.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventsRepository extends CrudRepository<Event> {


Event findByTitle(String title);
Event findByStartDate (LocalDate startDate);

List findAll();


}
