package eu.powerdot.search.rest;

import eu.powerdot.search.data.entity.UserDbo;
import eu.powerdot.search.data.repository.UserRepository;
import eu.powerdot.search.dto.UserDto;
import eu.powerdot.search.mapper.UserMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserApi {
    private final UserRepository userRepository;

    public UserApi(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("users")
    @Transactional
    public List<UserDto> searchUser() {
        List<UserDbo> allUsers = userRepository.findAll();
        return UserMapper.INSTANCE.map(allUsers);
    }

    @PostMapping("/users/activate")
    @Transactional
    public List<UserDto> activateUsers(@RequestBody String[] names) {
        List<UserDbo> allUsers = userRepository.findByName(names);
        allUsers.stream().forEach(user -> user.setActive(true));
        return UserMapper.INSTANCE.map(allUsers);
    }

}
