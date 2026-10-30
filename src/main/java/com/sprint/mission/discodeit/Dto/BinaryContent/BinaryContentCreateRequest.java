package com.sprint.mission.discodeit.Dto.BinaryContent;

public record BinaryContentCreateRequest(
        String fileName,
        String extension,
        byte[] content
) {
    public BinaryContentCreateRequest {
        if (fileName == null || fileName.isBlank()) {
            throw new IllegalArgumentException("파일 이름은 공백일 수 없습니다.");
        }
    }
}
