package com.sprint.mission.discodeit.Dto.User;

import com.sprint.mission.discodeit.entity.User;

import java.util.UUID;

public record UserFindResponse(
        UUID id,
        String name,
        String email,
        boolean isOnline
) {
    public static UserFindResponse of(User user, boolean isOnline) {
        return new UserFindResponse(user.getId(), user.getName(), user.getEmail(), isOnline);
    }
}
