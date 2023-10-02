package planer;

import controllers.EventController;
import repositories.EventsRepository;
import impl.EventRepositoryListImpl;
import services.impl.EventServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    EventsRepository eventsRepository = new EventRepositoryListImpl();
    EventServiceImpl eventService =new EventServiceImpl(eventsRepository);
    EventController createEventController = new EventController(scanner, eventService);
        System.out.println("Enter command: /add");
    String command = scanner.nextLine();
        if (command.equals("/add")){
            createEventController.createEvent();

        }
    }
}
