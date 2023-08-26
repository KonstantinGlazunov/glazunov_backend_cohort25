package de.ait.controllers;

import de.ait.models.Event;
import de.ait.services.EventService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class EventController {
    private final Scanner scanner;

    private final EventService eventService;

    public EventController(Scanner scanner, EventService eventService) {
        this.scanner = scanner;
        this.eventService = eventService;
    }

    public void addEvent() {
        System.out.println("Enter Event title");
        String title = scanner.nextLine();
        System.out.println("Enter start date yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());
        System.out.println("Enter end date yyyy-MM-dd");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());
        Event event = eventService.createEvent(title, startDate, endDate);
        System.out.println(event);
    }

public void printAllEvents(){
        List events = eventService.findAll();
    for (int i = 0; i < events.size(); i++) {
        System.out.println(events.get(i));
    }


    System.out.println(eventService.findAll().size());
}


}
