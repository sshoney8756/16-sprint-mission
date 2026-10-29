package com.sprint.mission.discodeit.Dto.Message;

import java.util.UUID;

public record MessageUpdateRequest(
        UUID id,
        String content
) {
    public MessageUpdateRequest {
        if (id == null) {
            throw new IllegalArgumentException("메세지를 수정할 유저 아이디의 값이 없습니다.");
        }
    }
}
