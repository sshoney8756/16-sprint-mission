package com.sprint.mission.discodeit.Dto.Channel;

import java.util.UUID;

public record ChannelFindAllRequest(
        UUID userId
) {
    public ChannelFindAllRequest {
        if (userId == null) {
            throw new IllegalArgumentException("조회를 요청하는 유저 아이디가 없습니다.");
        }
    }
}
