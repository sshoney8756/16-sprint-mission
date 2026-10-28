package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class BasicUserService implements UserService {
    private final UserRepository userRepository;
    private final ChannelRepository channelRepository;

    @Override
    public User create(String name, String email, String password) {
        User user = new User(name, email, password);
        return this.userRepository.save(user);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return this.userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User update(UUID id, String newName, String newEmail, String newPassword) {
        User user = this.userRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("수정할 유저가 존재하지 않습니다."));
        user.validateNotDeleted();
        user.update(newName, newEmail, newPassword);
        return this.userRepository.update(id, user);
    }

    @Override
    public void delete(UUID id) {
        User user = this.userRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("삭제할 유저가 존재하지 않습니다."));
        user.validateNotDeleted();

        // 유저 삭제 시 가입된 채널들에서도 유저 탈퇴
        Set<UUID> channels = new HashSet<>(user.getChannels());
        channels.forEach(channelId -> this.channelRepository.findById(channelId).ifPresent(channel -> {
            channel.removeUser(id);
            this.channelRepository.update(channelId, channel);
        }));

        this.userRepository.delete(id);
    }
}
