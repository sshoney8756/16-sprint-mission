package com.sprint.mission.discodeit.Dto.Message;

import com.sprint.mission.discodeit.Dto.BinaryContent.BinaryContentCreateRequest;

import java.util.List;

public record MessageAttachmentsCreateRequest(
        List<BinaryContentCreateRequest> files
) {
}
