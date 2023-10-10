package de.ait.task_05.services;

import de.ait.task_05.dtos.NewParticipantDto;
import de.ait.task_05.dtos.ParticipantDto;
import de.ait.task_05.models.Participant;

public interface ParticipantService {
    ParticipantDto register(NewParticipantDto newParticipant);
}
