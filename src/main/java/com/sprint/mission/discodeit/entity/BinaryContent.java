package com.sprint.mission.discodeit.entity;

import lombok.Getter;

@Getter
public class BinaryContent extends BaseEntity {
    private final byte[] content;

    public BinaryContent(byte[] content) {
        this.content = content;
    }
}
