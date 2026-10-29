package com.sprint.mission.discodeit.Dto.Channel;

import java.util.UUID;

public record ChannelFindRequest(
        UUID id
) {
    public ChannelFindRequest {
        if (id == null) {
            throw new IllegalArgumentException("조회를 요청하는 채널 아이디가 없습니다.");
        }
    }
}
