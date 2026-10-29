package com.sprint.mission.discodeit.Dto.Channel;

import java.util.List;
import java.util.UUID;

public record ChannelPrivateCreateRequest(
        List<UUID> memberIds
) {
}
