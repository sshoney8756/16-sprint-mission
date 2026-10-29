package com.sprint.mission.discodeit.Dto.ReadStatus;

import com.sprint.mission.discodeit.entity.ReadStatus;

import java.time.Instant;
import java.util.UUID;

public record ReadStatusCreateResponse(
        UUID userId,
        UUID channelId,
        Instant lastReadTime
) {
    public static ReadStatusCreateResponse from(ReadStatus readStatus) {
        return new ReadStatusCreateResponse(
                readStatus.getUserId(),
                readStatus.getChannelId(),
                readStatus.getLastReadTime()
        );
    }
}
