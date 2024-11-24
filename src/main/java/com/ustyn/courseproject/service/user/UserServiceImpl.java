package com.ustyn.courseproject.service.user;
import com.ustyn.courseproject.document.user.Key;
import com.ustyn.courseproject.document.user.Role;
import com.ustyn.courseproject.document.user.User;
import com.ustyn.courseproject.repository.RoleRepository;
import com.ustyn.courseproject.repository.UserRepository;
import com.ustyn.courseproject.service.key.KeyService;
import com.ustyn.courseproject.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final KeyService keyService;
    RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           KeyService keyService,
                           RoleService roleService) {

        this.userRepository = userRepository;
        this.keyService = keyService;
        this.roleService = roleService;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public User save(User user) {

        if(user.getPassword().getId() != null) {
            Key savedKey = keyService.findById(user.getPassword().getId());
            savedKey.setPassword(user.getPassword().getPassword());
            keyService.save(savedKey);
        } else {
            keyService.save(user.getPassword());
        }

        if(user.getRoles().getFirst().getId() != null) {
            Role savedRole = roleService.findById(user.getRoles().getFirst().getId());
            savedRole.setName(user.getRoles().getFirst().getName());
            roleService.save(savedRole);
        } else {
            roleService.save(user.getRoles().getFirst());
        }

        // Save the User
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(String id) {

        User user = userRepository.findById(id).orElse(null);

        System.out.println("user: " + user);
        if(user != null) {
            System.out.println("user not null; deleting role and key");
            roleService.deleteById(user.getRoles().stream().map(Role::getId).toList().getFirst());
            keyService.delete(user.getPassword());
        }

        userRepository.deleteById(id);
    }
}
