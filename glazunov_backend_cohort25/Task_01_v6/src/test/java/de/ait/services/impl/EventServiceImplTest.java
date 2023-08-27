package de.ait.services.impl;

import de.ait.models.Event;
import de.ait.repositories.EventsRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayName("EventServiceImpl Is Works...")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
class EventServiceImplTest {

    private EventServiceImpl eventService;

    @BeforeEach
    public void setUp() {
        EventsRepository eventsRepository = Mockito.mock(EventsRepository.class);
        when(eventsRepository.findByTitle("Test title")).thenReturn(new Event("Test title",
                LocalDate.of(2023, 12, 03),
                LocalDate.of(2023, 12, 02)));
        when(eventsRepository.findByTitle("Test no title")).thenReturn(null);
        this.eventService = new EventServiceImpl(eventsRepository);
    }

    @Test
    public void create_event_with_incorrect_dates_throws_exeption() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> eventService.createEvent("Test title",
                LocalDate.of(2023, 12, 03),
                LocalDate.of(2023, 12, 02)));
    }

    @Test
    public void create_event_without_title_throws_exeption() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> eventService.createEvent(" ",
                LocalDate.of(2023, 12, 03),
                LocalDate.of(2023, 12, 02)));
    }

    @Test
    public void create_event_returns_created_event() {

        assertThrows(IllegalArgumentException.class, () -> eventService.createEvent("Test no title",
                LocalDate.of(2023, 12, 03),
                LocalDate.of(2023, 12, 02)));


    }

    @Test
    public void add_existed_title_event_throw_exeption() {
        assertThrows(IllegalArgumentException.class, () -> eventService.createEvent("Test title",
                LocalDate.of(2023, 12, 03),
                LocalDate.of(2023, 12, 02)));
    }
}