package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.Dto.User.*;

import java.util.UUID;

public interface UserService {
    UserCreateResponse create(UserCreateRequest userCreateRequest, UserProfileImageCreateRequest userProfileImageCreateRequest);

    UserUpdateResponse update(UserUpdateRequest userUpdateRequest, UserProfileImageUpdateRequest userProfileImageUpdateRequest);

    UserFindResponse findById(UUID id);

    UserFindAllResponse findAll();

    void delete(UUID id);
}
