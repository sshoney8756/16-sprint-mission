package com.sprint.mission.discodeit.Dto.User;

import com.sprint.mission.discodeit.Dto.BinaryContent.BinaryContentCreateRequest;

public record UserProfileImageCreateRequest(
        BinaryContentCreateRequest binaryContentCreateRequest
) {
    public UserProfileImageCreateRequest {
        //TODO: 바이트 데이터가 이미지인지 검증
    }
}
