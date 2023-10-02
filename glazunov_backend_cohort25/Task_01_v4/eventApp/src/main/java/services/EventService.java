package services;

import models.Event;

import java.time.LocalDate;

public interface EventService {

    Event createEvent (String title, LocalDate startDate, LocalDate endDate);
}
