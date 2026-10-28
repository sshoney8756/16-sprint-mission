package com.sprint.mission.discodeit.Dto.User;

import com.sprint.mission.discodeit.entity.User;

import java.util.UUID;

public record UserAuthResponse(
        UUID id,
        String name,
        String email
) {
    public static UserAuthResponse from(User user) {
        return new UserAuthResponse(user.getId(), user.getName(), user.getEmail());
    }
}
