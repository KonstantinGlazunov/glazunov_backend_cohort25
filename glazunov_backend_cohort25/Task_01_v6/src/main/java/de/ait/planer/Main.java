package de.ait.planer;

import de.ait.controllers.EventController;
import de.ait.repositories.impl.EventRepositoryFileImpl;
import de.ait.repositories.impl.EventRepositoryListImpl;
import de.ait.repositories.EventsRepository;
import de.ait.services.impl.EventServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        EventsRepository eventsRepositoryList = new EventRepositoryListImpl();
        EventsRepository eventsRepositoryFile = new EventRepositoryFileImpl("events.txt");
        EventServiceImpl eventService = new EventServiceImpl(eventsRepositoryFile);
        EventController eventController = new EventController(scanner, eventService);
        System.out.println("Enter command: /add /prn /exit");

        while (true) {
            String command = scanner.nextLine();
            if (command.equals("/prn")) {
                eventController.printAllEvents();
            } else if (command.equals("/add")) {
                eventController.addEvent();
            } else if (command.equals("/exit")) {
                break;
            }

        }
    }
}
