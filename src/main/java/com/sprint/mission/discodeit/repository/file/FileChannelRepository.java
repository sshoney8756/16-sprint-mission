package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.*;

@Repository
public class FileChannelRepository extends FileRepository<Channel> implements ChannelRepository {
    public FileChannelRepository() {
        super("./data/channel.ser", new HashMap<>());
    }

    @Override
    public Channel save(Channel channel) {
        Map<UUID, Channel> channels = this.readFile(filePath);
        Channel existingChannel = channels.putIfAbsent(channel.getId(), channel);
        if (existingChannel != null) throw new IllegalStateException("이미 저장된 채널입니다.");
        this.writeFile(channels, filePath);
        return channel;
    }

    @Override
    public Channel update(UUID id, Channel newChannel) {
        Map<UUID, Channel> channels = this.readFile(filePath);
        channels.replace(id, newChannel);
        this.writeFile(channels, filePath);
        return newChannel;
    }

    @Override
    public Optional<Channel> findById(UUID id) {
        Map<UUID, Channel> channels = this.readFile(filePath);
        Channel channel = channels.get(id);
        if (channel != null && !channel.isDeleted()) return Optional.of(channel);
        return Optional.empty();
    }

    @Override
    public List<Channel> findAll() {
        Map<UUID, Channel> channels = this.readFile(filePath);
        return channels.values().stream().filter(channel -> !channel.isDeleted()).toList();
    }

    @Override
    public List<Channel> findAllByUserId(UUID userId) {
        Map<UUID, Channel> channels = this.readFile(filePath);
        return channels.values()
                .stream()
                .filter(channel -> channel.getUsers().contains(userId))
                .filter(channel -> !channel.isDeleted())
                .toList();
    }

    @Override
    public void delete(UUID id) {
        Map<UUID, Channel> channels = this.readFile(filePath);
        Channel channel = channels.get(id);
        if (channel == null || channel.isDeleted()) throw new IllegalArgumentException("삭제할 채널이 존재하지 않습니다.");
        channel.softDelete();
        this.writeFile(channels, filePath);
    }
}
