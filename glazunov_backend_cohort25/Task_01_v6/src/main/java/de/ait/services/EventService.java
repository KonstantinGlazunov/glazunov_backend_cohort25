package de.ait.services;

import de.ait.models.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventService {

    Event createEvent (String title, LocalDate startDate, LocalDate endDate);

   List <Event> findAll ();
}
