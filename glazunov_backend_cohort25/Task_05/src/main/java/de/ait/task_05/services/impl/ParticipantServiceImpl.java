package de.ait.task_05.services.impl;

import de.ait.task_05.dtos.NewParticipantDto;
import de.ait.task_05.dtos.ParticipantDto;
import de.ait.task_05.exeptions.RestExeption;
import de.ait.task_05.models.Event;
import de.ait.task_05.models.Participant;
import de.ait.task_05.repositories.ParticipantsRepository;
import de.ait.task_05.services.EventService;
import de.ait.task_05.services.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantsRepository participantsRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public ParticipantDto register(NewParticipantDto newParticipant) {
        if (participantsRepository.existsByEmail(newParticipant.getEmail())){
            throw new RestExeption(HttpStatus.CONFLICT,
                    "Participant with email <" + newParticipant.getEmail() + "> already exist");
        }
        Participant participant = Participant.builder()
                .name(newParticipant.getName())
                .email(newParticipant.getEmail())
                .password(passwordEncoder.encode(newParticipant.getPassword()))
                .role(Participant.Role.USER)
                .build();
        participantsRepository.save(participant);
        return ParticipantDto.from(participant);
    }


}
