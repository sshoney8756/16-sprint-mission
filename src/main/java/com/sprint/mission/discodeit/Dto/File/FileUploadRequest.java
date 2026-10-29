package com.sprint.mission.discodeit.Dto.File;

public record FileUploadRequest(
        String fileName,
        String extension,
        byte[] content
) {
    public FileUploadRequest {
        if (fileName == null || fileName.isBlank()) {
            throw new IllegalArgumentException("파일 이름은 공백일 수 없습니다.");
        }
    }
}
