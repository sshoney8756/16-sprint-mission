package com.sprint.mission.discodeit.Dto.Channel;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.ChannelType;

import java.util.List;
import java.util.UUID;

public record ChannelPrivateCreateResponse(
        UUID id,
        UUID userId,
        List<UUID> memberIds,
        ChannelType type
) {
    public static ChannelPrivateCreateResponse of(Channel channel, UUID userId) {
        return new ChannelPrivateCreateResponse(
                channel.getId(),
                userId,
                List.copyOf(channel.getUsers()),
                channel.getType()
        );
    }
}
