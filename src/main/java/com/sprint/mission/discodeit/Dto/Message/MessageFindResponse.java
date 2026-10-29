package com.sprint.mission.discodeit.Dto.Message;

import com.sprint.mission.discodeit.entity.Message;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record MessageFindResponse(
        UUID id,
        UUID authorId,
        UUID channelId,
        String content,
        Instant updatedAt,
        List<UUID> attachmentsIds
) {
    public static MessageFindResponse from(Message message) {
        return new MessageFindResponse(
                message.getId(),
                message.getAuthorId(),
                message.getChannelId(),
                message.getContent(),
                message.getUpdatedAt(),
                List.copyOf(message.getAttachmentsIds())
        );
    }
}
