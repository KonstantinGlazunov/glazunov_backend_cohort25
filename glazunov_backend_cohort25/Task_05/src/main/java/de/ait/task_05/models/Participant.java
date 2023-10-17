package de.ait.task_05.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "participant")
//@EqualsAndHashCode(exclude = "events")
public class Participant {

    public enum Role {
        ADMIN, MANAGER, USER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(length = 20, nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;


    @ManyToMany
    @JoinTable(
            name = "participant_event",
            joinColumns =
            @JoinColumn(name = "participant_id", nullable = false, referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "event_id", nullable = false, referencedColumnName = "id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"participant_id", "event_id"})
    )
    @EqualsAndHashCode.Exclude
    private Set<Event> events;
}
