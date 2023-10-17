package de.ait.task_05.security.details;

import de.ait.task_05.models.Participant;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AuthenticatedParticipant implements UserDetails {

    private final Participant participant;

    public AuthenticatedParticipant(Participant participant) {
        this.participant = participant;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    /*String role = participant.getRole().toString();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
      List <GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);*/

        return Collections.singleton(new SimpleGrantedAuthority(participant.getRole().toString()));
    }

    @Override
    public String getPassword() {

        return participant.getPassword();
    }

    @Override
    public String getUsername() {
        return participant.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }
}
