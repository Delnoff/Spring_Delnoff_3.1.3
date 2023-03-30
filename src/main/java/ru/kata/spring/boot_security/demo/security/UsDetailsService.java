package ru.kata.spring.boot_security.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Optional;

@Service
public class UsDetailsService implements UserDetailsService {

    private final UsRepository usRepository;

    @Autowired
    public UsDetailsService(UsRepository usRepository) {
        this.usRepository = usRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = usRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        return new UsDetails(user.get());
    }


}
