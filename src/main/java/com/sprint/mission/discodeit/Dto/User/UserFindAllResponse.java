package com.sprint.mission.discodeit.Dto.User;

import java.util.List;

public record UserFindAllResponse(
        List<UserFindResponse> users
) {
    public static UserFindAllResponse from(List<UserFindResponse> userFindResponses) {
        return new UserFindAllResponse(userFindResponses);
    }
}
