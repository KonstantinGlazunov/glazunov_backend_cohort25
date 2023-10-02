package de.ait.task_05.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String title;

    private LocalDate date;

    @ManyToMany(mappedBy = "events")
    private Set<Participant> participants;

    @ManyToOne
    @JoinColumn(name = "site_id", nullable = false)
    private Site site;

}
