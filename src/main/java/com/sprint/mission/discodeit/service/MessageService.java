package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.Message;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageService {
    Message create(UUID authorId, String content, UUID channelId);

    Message update(UUID id, String newContent);

    Optional<Message> findById(UUID id);

    List<Message> findAll();

    void delete(UUID id);
}
