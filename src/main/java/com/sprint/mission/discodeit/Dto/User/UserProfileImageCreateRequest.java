package com.sprint.mission.discodeit.Dto.User;

import com.sprint.mission.discodeit.Dto.File.FileUploadRequest;

public record UserProfileImageCreateRequest(
        FileUploadRequest fileUploadRequest
) {
    public UserProfileImageCreateRequest {
        //TODO: 바이트 데이터가 이미지인지 검증
    }
}
