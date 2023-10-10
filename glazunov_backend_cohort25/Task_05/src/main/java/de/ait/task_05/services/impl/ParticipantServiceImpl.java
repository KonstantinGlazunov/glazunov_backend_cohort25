package de.ait.task_05.services.impl;

import de.ait.task_05.dtos.NewParticipantDto;
import de.ait.task_05.dtos.ParticipantDto;
import de.ait.task_05.exeptions.RestExeption;
import de.ait.task_05.models.Participant;
import de.ait.task_05.repositories.ParticipantsRepository;
import de.ait.task_05.services.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantsRepository participantsRepository;
    @Override
    public ParticipantDto register(NewParticipantDto newParticipant) {
        if (participantsRepository.existsByEmail(newParticipant.getEmail())){
            throw new RestExeption(HttpStatus.CONFLICT,
                    "Participant with email <" + newParticipant.getEmail() + "> already exist");
        }
        Participant participant = Participant.builder()
                .name(newParticipant.getName())
                .email(newParticipant.getEmail())
                .build();
        participantsRepository.save(participant);
        return ParticipantDto.from(participant);
    }
}
