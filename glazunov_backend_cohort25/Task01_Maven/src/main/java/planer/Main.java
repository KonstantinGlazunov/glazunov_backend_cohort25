package planer;

import planer.controllers.EventController;
import planer.repositories.EventsRepository;
import planer.repositories.impl.EventRepositoryListImpl;
import planer.services.impl.EventServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    EventsRepository eventsRepository = new EventRepositoryListImpl();
    EventServiceImpl eventCreateService =new EventServiceImpl(eventsRepository);
    EventController createEventController = new EventController(scanner, eventCreateService);
        System.out.println("Enter command: /add");
    String command = scanner.nextLine();
        if (command.equals("/add")){
            createEventController.createEvent();

        }
    }
}
