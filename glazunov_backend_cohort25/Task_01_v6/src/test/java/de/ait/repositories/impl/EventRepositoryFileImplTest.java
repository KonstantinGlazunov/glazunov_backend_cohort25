package de.ait.repositories.impl;

import de.ait.models.Event;
import org.junit.jupiter.api.*;

import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Events Repository FileImpl Is Works...")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
class EventRepositoryFileImplTest {

    private static final String TEMP_EVENT_FILE_NAME = "test_events.txt";
    private EventRepositoryFileImpl eventRepository;

    @BeforeEach
    public void setUp() throws Exception {
        createNewFileForTest(TEMP_EVENT_FILE_NAME);
        eventRepository = new EventRepositoryFileImpl(TEMP_EVENT_FILE_NAME);
    }

    @AfterEach
    public void tearDown() throws Exception {
        deleteFileAfterTest(TEMP_EVENT_FILE_NAME);
    }

    @DisplayName("save(): ")
    @Nested
    class SaveTest {

        @Test
        public void event_writes_correct_line_to_file() throws Exception {

            Event event = new Event("Test title",
                    LocalDate.of(2023, 12, 01),
                    LocalDate.of(2023, 12, 02),
                    1L);
            eventRepository.save(event);

            String expectetd = "Test title#2023-12-01#2023-12-02#1";
            BufferedReader reader = new BufferedReader(new FileReader(TEMP_EVENT_FILE_NAME));
            String actual = reader.readLine();
            reader.close();
            assertEquals(expectetd, actual);
        }
    }


    @DisplayName("findAll():")
    @Nested
    class findAllTests {

        @Test
        public void returns_correct_list_of_events() throws Exception {
            BufferedWriter writer = new BufferedWriter(new FileWriter(TEMP_EVENT_FILE_NAME));

            writer.write("test title1#2023-12-02#2023-12-03#1\n" +
                    "test Title2#2023-12-02#2023-12-03#2");
            writer.close();

            List<Event> actual = eventRepository.findAll();
            List<Event> expected = Arrays.asList(
                    new Event("test title1", LocalDate.of(2023, 12, 02), LocalDate.of(2023, 12, 03), 1L),
                    new Event("test Title2", LocalDate.of(2023, 12, 02), LocalDate.of(2023, 12, 03), 2L)
            );
            deleteFileAfterTest(TEMP_EVENT_FILE_NAME);
            assertEquals(actual, expected);
        }
    }

    private static void createNewFileForTest(String fileNAme) throws IOException {
        File file = new File(fileNAme);

        deleteFileIfExist(file);
        boolean result = file.createNewFile();
        if (!result) {
            throw new IllegalStateException("Problem with file create");
        }
    }

    private static void deleteFileIfExist(File file) {
        if (file.exists()) {
            boolean result = file.delete();
            if (!result) {
                throw new IllegalStateException("Problem with file delete");
            }
        }
    }

    private void deleteFileAfterTest(String fileName) throws Exception {
        File file = new File(fileName);
        deleteFileIfExist(file);
    }

}