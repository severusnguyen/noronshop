package com.example.noronshopconfig.config;

import com.example.noronshopcommons.data.tables.pojos.Role;
import com.example.noronshopcommons.data.tables.pojos.User;
import com.example.noronshoprepository.repository.account.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Optional<User> user = userRepository.findUserByUsername(username);

            if (user.isEmpty()) {
                throw new UsernameNotFoundException("User don't exist!");
            }

            List<Role> roles = userRepository.findRolesByUserId(user.get().getId());
            return new UserDetailsImp(user.get(), roles);
        } catch (Exception e){
            throw new UsernameNotFoundException("Don't found.");
        }
    }
}
