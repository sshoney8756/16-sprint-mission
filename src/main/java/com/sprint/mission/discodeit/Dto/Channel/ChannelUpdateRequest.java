package com.sprint.mission.discodeit.Dto.Channel;

import java.util.UUID;

public record ChannelUpdateRequest(
        UUID id,
        String name,
        String description
) {
    public ChannelUpdateRequest {
        if (id == null) {
            throw new IllegalArgumentException("수정할 채널의 아이디가 없습니다.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("채널 이름은 공백일 수 없습니다.");
        }
    }
}
