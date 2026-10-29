package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.BinaryContent;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BinaryContentRepository {

    BinaryContent save(BinaryContent binaryContent);

    BinaryContent update(UUID id, BinaryContent binaryContent);

    Optional<BinaryContent> findById(UUID id);

    Optional<BinaryContent> findByUserId(UUID userId);

    List<BinaryContent> findAll();

    List<BinaryContent> findAllByMessageId(UUID messageId);

    void delete(UUID id);
}
