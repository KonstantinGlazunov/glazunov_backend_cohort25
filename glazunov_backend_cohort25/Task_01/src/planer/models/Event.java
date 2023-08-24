package planer.models;

import java.time.LocalDate;
import java.util.Objects;

public class Event {
    private Long id;
    private String titel;

    private LocalDate startDate;
    private LocalDate endDate;

    public Event( String titel, LocalDate startDate, LocalDate endDate) {
        this.titel = titel;
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End data can not be before start data");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event(String titel) {
        this.titel = titel;
    }




    public void setId(Long id) {
        this.id = id;
    }

    public void setTitel(String titel) {
        this.titel = titel;
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

    public String getTitel() {
        return titel;
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
        return Objects.equals(id, event.id) && Objects.equals(titel, event.titel) && Objects.equals(startDate, event.startDate) && Objects.equals(endDate, event.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titel, startDate, endDate);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", titel='" + titel + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
