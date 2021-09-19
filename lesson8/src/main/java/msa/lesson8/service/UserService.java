package msa.lesson8.service;

import msa.lesson8.domain.Role;
import msa.lesson8.domain.User;
import msa.lesson8.repository.UserRepository;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        userRepository.save(User.builder()
                .username("user1")
                .password(new BCryptPasswordEncoder().encode("111"))
                .authorities(ImmutableList.of(Role.USER))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build());

        userRepository.save(User.builder()
                .username("user2")
                .password(new BCryptPasswordEncoder().encode("222"))
                .authorities(ImmutableList.of(Role.USER))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build());


    }

    @Override
    public UserDetails loadUserByUsername(String s) {
        return userRepository.findByUsername(s).orElseThrow(() ->
                new UsernameNotFoundException("Неверный логин или пароль"));
    }
}

