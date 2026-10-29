package com.sprint.mission.discodeit.Dto.ReadStatus;

import java.time.Instant;
import java.util.UUID;

public record ReadStatusUpdateByUserIdRequest(
        UUID userId,
        Instant lastReadTime
) {
    public ReadStatusUpdateByUserIdRequest {
        if (userId == null) {
            throw new NullPointerException("메세지 확인 기록을 수정할 유저 아이디 값이 없습니다.");
        }
        if (lastReadTime == null) {
            throw new NullPointerException("갱신할 메세지 확인 기록의 값이 없습니다.");
        }
    }
}
