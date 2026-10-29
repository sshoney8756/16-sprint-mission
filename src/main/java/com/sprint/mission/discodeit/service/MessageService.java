package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.Dto.Message.*;
import com.sprint.mission.discodeit.entity.Message;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageService {
    MessageCreateResponse create(MessageCreateRequest messageCreateRequest, MessageAttachmentsCreateRequest messageAttachmentsCreateRequest);

    MessageUpdateResponse update(MessageUpdateRequest messageUpdateRequest);

    MessageFindResponse findById(UUID id);

    List<MessageFindResponse> findAllByChannelId(UUID channelId);

    void delete(UUID id);
}
