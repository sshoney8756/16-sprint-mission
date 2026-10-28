package com.sprint.mission.discodeit.Dto.User;

import com.sprint.mission.discodeit.entity.User;

import java.util.UUID;

public record UserCreateResponse(
        UUID id,
        String name,
        String email,
        UUID profileId
) {
    public static UserCreateResponse of(User user, UUID profileId) {
        return new UserCreateResponse(user.getId(), user.getName(), user.getEmail(), profileId);
    }
}
