package de.ait.repositories.impl;

import de.ait.models.Event;
import de.ait.repositories.EventsRepository;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class EventRepositoryFileImpl implements EventsRepository {
    private final String fileName;

    public EventRepositoryFileImpl(String fileName) {
        this.fileName = fileName;
    }

    private Long generatedId = 1l;

    @Override
    public Event findBiId(Long id) {
        return null;
    }

    @Override
    public List<Event> findAll() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.lines()
                    .map(line -> line.split(("#")))
                    .map(parsed -> new Event(parsed[0], LocalDate.parse(parsed[1]), LocalDate.parse(parsed[2]), Long.parseLong(parsed[3])))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalStateException("File read troubles " + e.getMessage());
        }
    }

    @Override
    public void save(Event event) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            event.setId(generatedId);
            writer.write(event.getTitle() + "#" + event.getStartDate() + "#" + event.getEndDate() + "#" + event.getId());
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalStateException("File save troubles " + e.getMessage());
        }
        generatedId++;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void upDate(Event model) {

    }

    @Override
    public Event findByTitle(String title) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.lines()
                    .map(line -> line.split(("#")))
                    .filter(parsed -> parsed[1].equals(title))
                    .findFirst()
                    .map(parsed -> new Event(parsed[0], LocalDate.parse(parsed[1]), LocalDate.parse(parsed[2]), Long.parseLong(parsed[3])))
                    .orElse(null);


        } catch (IOException e) {
            throw new IllegalStateException("File read troubles " + e.getMessage());
        }
    }

    @Override
    public Event findByStartDate(LocalDate startDate) {
        return null;
    }
}
