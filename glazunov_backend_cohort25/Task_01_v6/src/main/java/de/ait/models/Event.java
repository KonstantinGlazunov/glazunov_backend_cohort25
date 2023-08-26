package de.ait.models;



import java.time.LocalDate;
import java.util.Objects;

public class Event {
    private Long id;
    private String title;

    private LocalDate startDate;
    private LocalDate endDate;

    public Event( String title, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public Event( String title, LocalDate startDate, LocalDate endDate, Long id) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.id = id;
    }

    public Event(String title) {
        this.title = title;
    }




    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event event)) return false;
        return Objects.equals(id, event.id) && Objects.equals(title, event.title) && Objects.equals(startDate, event.startDate) && Objects.equals(endDate, event.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, startDate, endDate);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
