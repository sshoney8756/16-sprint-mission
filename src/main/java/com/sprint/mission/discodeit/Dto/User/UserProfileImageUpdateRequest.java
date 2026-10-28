package com.sprint.mission.discodeit.Dto.User;

import java.util.UUID;

public record UserProfileImageUpdateRequest(
        UUID userId,
        String fileName,
        String extension,
        byte[] content
) {
}
