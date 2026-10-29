package com.sprint.mission.discodeit.Dto.Message;

import com.sprint.mission.discodeit.Dto.File.FileUploadRequest;

import java.util.List;

public record MessageAttachmentsCreateRequest(
        List<FileUploadRequest> files
) {
}
