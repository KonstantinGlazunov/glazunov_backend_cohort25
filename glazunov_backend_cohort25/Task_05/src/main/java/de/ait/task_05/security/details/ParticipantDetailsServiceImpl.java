package de.ait.task_05.security.details;

import de.ait.task_05.models.Participant;
import de.ait.task_05.repositories.ParticipantsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParticipantDetailsServiceImpl implements UserDetailsService {

    private final ParticipantsRepository participantsRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      /*  Optional<Participant> participantOptional = participantsRepository.findByEmail(email);
        if(participantOptional.isPresent()) {
            Participant participant = participantOptional.get();
            AuthenticatedParticipant authenticatedParticipant = new AuthenticatedParticipant(participant);
            return authenticatedParticipant;
            }else {
            throw new UsernameNotFoundException("Participant with email <" + email + "> not found");
        }*/

        Participant participant = participantsRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Participant with email <" + email + "> not found"));
        return new AuthenticatedParticipant(participant);
    }

}
