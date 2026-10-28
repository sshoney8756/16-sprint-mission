package com.sprint.mission.discodeit.Dto.User;

import com.sprint.mission.discodeit.entity.User;

import java.util.UUID;

public record UserUpdateResponse(
        UUID id,
        String name,
        String email,
        UUID profileId
) {
    public static UserUpdateResponse of(User user, UUID profileId) {
        return new UserUpdateResponse(user.getId(), user.getName(), user.getEmail(), profileId);
    }
}
