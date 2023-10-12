package de.ait.task_05.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany (mappedBy = "site")
    @EqualsAndHashCode.Exclude
    private Set<Event> events;
}
