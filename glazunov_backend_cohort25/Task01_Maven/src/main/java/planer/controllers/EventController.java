package planer.controllers;

import planer.models.Event;
import planer.services.impl.EventServiceImpl;

import java.time.LocalDate;
import java.util.Scanner;

public class EventController {
    private final Scanner scanner;
    private final EventServiceImpl eventCreateService;

    public EventController(Scanner scanner, EventServiceImpl eventCreateService) {
        this.scanner = scanner;
        this.eventCreateService = eventCreateService;
    }

    public void createEvent() {
        System.out.println("Enter Event title");
        String title = scanner.nextLine();
        System.out.println("Enter start date yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());
        System.out.println("Enter end date yyyy-MM-dd");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());
        Event event = eventCreateService.createEvent(title, startDate, endDate);
        System.out.println(event);
    }




}
