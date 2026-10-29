package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.Dto.Auth.UserAuthRequest;
import com.sprint.mission.discodeit.Dto.Auth.UserAuthResponse;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;

    public UserAuthResponse login(UserAuthRequest userAuthRequest) {
        String name = userAuthRequest.name();
        String password = userAuthRequest.password();

        User user = this.userRepository.findByName(name).orElseThrow(
                () -> new NoSuchElementException("존재하지 않는 유저 이름입니다."));
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return UserAuthResponse.from(user);
    }
}
