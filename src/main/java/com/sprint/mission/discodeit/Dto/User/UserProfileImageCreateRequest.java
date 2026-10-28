package com.sprint.mission.discodeit.Dto.User;

public record UserProfileImageCreateRequest(
        String fileName,
        String extension,
        byte[] content
) {
    public UserProfileImageCreateRequest {
        if (fileName == null || fileName.isBlank()) {
            throw new IllegalArgumentException("파일 이름은 공백일 수 없습니다.");
        }

        //TODO: 바이트 데이터가 이미지인지 검증
    }
}
