package planer.repositories;

import planer.models.Event;

import java.time.LocalDate;

public interface EventRepository extends CrudRepository<Event> {


Event findByTitle(String title);
Event findByStartDate (LocalDate startDate);


}
