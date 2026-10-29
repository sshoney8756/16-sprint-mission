package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.ChannelType;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.service.ChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class BasicChannelService implements ChannelService {
    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;

    @Override
    public Channel create(String name, String description, ChannelType type) {
        Channel channel = new Channel(name, description, type);
        return this.channelRepository.save(channel);
    }

    @Override
    public Optional<Channel> findById(UUID id) {
        return this.channelRepository.findById(id);
    }

    @Override
    public List<Channel> findAll() {
        return this.channelRepository.findAll();
    }

    @Override
    public Channel update(UUID id, String newName, String newDescription) {
        Channel channel = this.channelRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("수정할 채널이 존재하지 않습니다."));
        channel.validateNotDeleted();
        channel.update(newName, newDescription);
        return this.channelRepository.update(id, channel);
    }

    @Override
    public void addUserToChannel(UUID channelId, UUID userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("채널에 추가할 유저가 존재하지 않습니다."));
        user.validateNotDeleted();

        Channel channel = this.channelRepository.findById(channelId).orElseThrow(
                () -> new NoSuchElementException("유저를 추가할 채널이 존재하지 않습니다."));
        channel.validateNotDeleted();

        channel.addUser(user.getId());
        this.channelRepository.update(channelId, channel);
    }

    @Override
    public void removeUserFromChannel(UUID channelId, UUID userId) {
        // 유저가 존재하는지 확인
        User user = this.userRepository.findById(userId).orElseThrow(
                () -> new NoSuchElementException("채널에서 탈퇴시킬 유저가 존재하지 않습니다."));
        user.validateNotDeleted();

        Channel channel = this.channelRepository.findById(channelId).orElseThrow(
                () -> new NoSuchElementException("유저가 탈퇴할 채널이 존재하지 않습니다."));
        channel.validateNotDeleted();

        channel.removeUser(userId);
        this.channelRepository.update(channelId, channel);
    }

    @Override
    public void delete(UUID id) {
        Channel channel = this.channelRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("삭제할 채널이 존재하지 않습니다."));
        channel.validateNotDeleted();
        this.channelRepository.delete(id);
    }
}
