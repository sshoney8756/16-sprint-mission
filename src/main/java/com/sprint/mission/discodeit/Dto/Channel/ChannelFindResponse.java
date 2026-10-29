package com.sprint.mission.discodeit.Dto.Channel;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.ChannelType;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record ChannelFindResponse(
        UUID id,
        String name,
        String description,
        ChannelType type,
        Instant lastMessageTime,
        List<UUID> memberIds
) {
    public static ChannelFindResponse of(Channel channel, Instant lastMessageTime) {
        List<UUID> memberIds = channel.getType() == ChannelType.PRIVATE
                ? channel.getUsers().stream().toList()
                : null;
        return new ChannelFindResponse(
                channel.getId(),
                channel.getName(),
                channel.getDescription(),
                channel.getType(),
                lastMessageTime,
                memberIds);
    }
}
