package planer.repositories;

import planer.models.Event;

import java.time.LocalDate;

public interface EventsRepository extends CrudRepository<Event> {


Event findByTitle(String title);
Event findByStartDate (LocalDate startDate);


}
