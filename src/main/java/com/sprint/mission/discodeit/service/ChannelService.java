package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.Dto.Channel.*;
import com.sprint.mission.discodeit.entity.Channel;

import java.util.List;
import java.util.UUID;

public interface ChannelService {
    ChannelPublicCreateResponse createPublic(ChannelPublicCreateRequest channelPublicCreateRequest);

    ChannelPrivateCreateResponse createPrivate(ChannelPrivateCreateRequest channelPrivateCreateRequest);

    ChannelUpdateResponse update(ChannelUpdateRequest channelUpdateRequest);

    void addUserToChannel(UUID channelId, UUID userId);

    void removeUserFromChannel(UUID channelId, UUID userId);

    ChannelFindResponse findById(UUID id);

    List<ChannelFindResponse> findAllByUserId(UUID userId);

    void delete(UUID id);
}
