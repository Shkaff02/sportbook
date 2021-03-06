package com.polytech.sportbook.service;

import com.polytech.sportbook.domain.Role;
import com.polytech.sportbook.domain.User;
import com.polytech.sportbook.repo.RoleRepository;
import com.polytech.sportbook.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to database", user.getUserName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRegisteredAt(LocalDateTime.now());
        return userRepository.save(user);

    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to database", role.getClass());
        return roleRepository.save(role);
    }

    @Override
    public List<Role> roles() {
        log.info("Fetching all roles from database");
        return roleRepository.findAll();
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        log.info("Adding role {} to user {}", roleName, userName);
        User user = userRepository.findByUserName(userName);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Override
    public User getUser(String userName) {
        log.info("Fetching user {}", userName);
        return userRepository.findByUserName(userName);
    }

    @Override
    public User getUserById(Long id) {
        log.info("Fetching user by id {}", id);
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> users() {
        log.info("Fetching all users from database");
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        log.info("Deleting user {}", id);
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(Long id, User user) {
        log.info("Updating user {}", id);
        User updated = userRepository.findById(id).orElse(null);
        if (updated != null) {
            updated.setFirstName(user.getFirstName());
            updated.setLastName(user.getLastName());
            updated.setParentName(user.getParentName());
            updated.setPhoneNumber(user.getPhoneNumber());
            updated.setEmail(user.getEmail());
            updated.setUserName(user.getUserName());
            updated.setRoles(user.getRoles());
            updated.setPassword(user.getPassword());
        }
        return userRepository.save(updated);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);

        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {authorities.add(new SimpleGrantedAuthority(role.getName()));});
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorities);
    }
}
