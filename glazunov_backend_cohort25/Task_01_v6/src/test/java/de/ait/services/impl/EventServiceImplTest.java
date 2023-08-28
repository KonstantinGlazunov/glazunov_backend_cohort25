package de.ait.services.impl;

import de.ait.models.Event;
import de.ait.repositories.EventsRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("EventServiceImpl Is Works...")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
class EventServiceImplTest {
    private EventsRepository eventsRepository;
    private EventServiceImpl eventService;

    private static final Event NOT_EXIST_EVENT = new Event("Test no title",
            LocalDate.of(2023, 12, 01),
            LocalDate.of(2023, 12, 03));
    private static final Event EXIST_EVENT =new Event("Test title",
              LocalDate.of(2023, 12, 01),
                LocalDate.of(2023, 12, 03));
    private static final LocalDate ERST_DATA = LocalDate.of(2023, 12, 01);
    private static final LocalDate LAST_DATA = LocalDate.of(2023, 12, 03);
    private static final String  EXIST_EVENT_TITLE = "Test title";
    private static final String  NOT_EXIST_EVENT_TITLE = "Test no title";


    @BeforeEach
    public void setUp() {
        eventsRepository = Mockito.mock(EventsRepository.class);
        when(eventsRepository.findByTitle(EXIST_EVENT_TITLE )).thenReturn(EXIST_EVENT);
        when(eventsRepository.findByTitle(NOT_EXIST_EVENT_TITLE)).thenReturn(null);
        this.eventService = new EventServiceImpl(eventsRepository);
    }

    @Nested
    @DisplayName(("createEvent"))
    class CreateEvent {
        @Test
        public void create_event_with_incorrect_dates_throws_exeption() throws Exception {
            assertThrows(IllegalArgumentException.class, () -> eventService.createEvent(EXIST_EVENT_TITLE,
                    ERST_DATA,
                    LAST_DATA));
        }

        @Test
        public void create_event_without_title_throws_exeption() throws Exception {
            assertThrows(IllegalArgumentException.class, () -> eventService.createEvent(" ",
                    ERST_DATA,
                    LAST_DATA));
        }

        @Test
        public void create_event_returns_created_event() {

            Event actual = eventService.createEvent(NOT_EXIST_EVENT_TITLE,
                    ERST_DATA,
                    LAST_DATA);
            verify(eventsRepository).save(NOT_EXIST_EVENT);
            assertNotNull(actual);
        }

        @Test
        public void add_existed_title_event_throw_exeption() {
            assertThrows(IllegalArgumentException.class, () -> eventService.createEvent("Test title",
                    LAST_DATA,
                    ERST_DATA));
        }
    }

}