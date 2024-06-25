package org.kst.lms.services;

import lombok.RequiredArgsConstructor;
import org.kst.lms.models.User;
import org.kst.lms.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User save(User user){
        return this.userRepository.save(user);
    }
}
