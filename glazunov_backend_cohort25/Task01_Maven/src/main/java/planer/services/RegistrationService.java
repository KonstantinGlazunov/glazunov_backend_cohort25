package planer.services;

import planer.models.Event;

import java.time.LocalDate;

public interface RegistrationService {

    Event createEvent (String title, LocalDate startDate, LocalDate endDate);
}
