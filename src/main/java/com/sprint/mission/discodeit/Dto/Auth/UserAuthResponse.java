package com.sprint.mission.discodeit.Dto.Auth;

import com.sprint.mission.discodeit.entity.User;

import java.util.UUID;

public record UserAuthResponse(
        UUID id,
        String name,
        String email,
        UUID profileId
) {
    public static UserAuthResponse from(User user) {
        return new UserAuthResponse(user.getId(), user.getName(), user.getEmail(), user.getProfileId());
    }
}
