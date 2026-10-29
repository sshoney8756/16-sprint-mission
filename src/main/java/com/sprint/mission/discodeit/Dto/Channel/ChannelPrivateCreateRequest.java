package com.sprint.mission.discodeit.Dto.Channel;

import java.util.List;
import java.util.UUID;

public record ChannelPrivateCreateRequest(
        UUID userId,
        List<UUID> memberIds
) {
    public ChannelPrivateCreateRequest {
        if (userId == null) {
            throw new NullPointerException("채널 생성을 요청하는 유저 아이디가 없습니다.");
        }
    }
}
