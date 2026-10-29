package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.ReadStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReadStatusRepository {
    ReadStatus save(ReadStatus readStatus);

    ReadStatus update(UUID id, ReadStatus readStatus);

    Optional<ReadStatus> findById(UUID id);

    Optional<ReadStatus> findByChannelIdAndUserId(UUID channelId, UUID userId);

    Optional<ReadStatus> findByUserId(UUID userId);

    List<ReadStatus> findAll();

    List<ReadStatus> findAllByChannelId(UUID channelId);

    void delete(UUID id);
}
