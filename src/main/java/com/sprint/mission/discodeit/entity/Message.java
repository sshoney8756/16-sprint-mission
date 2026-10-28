package com.sprint.mission.discodeit.entity;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Message extends BaseEntity {
    private UUID sentFromId;
    private UUID channelId;
    private String content;

    public Message(UUID sentFromId, String content, UUID channelId) {
        super();
        this.sentFromId = sentFromId;
        this.content = content;
        this.channelId = channelId;
    }

    public Message update(String newContent) {
        boolean updated = false;
        if (newContent != null && !newContent.equals(this.content)) {
            this.content = newContent;
            updated = true;
        }

        if (updated) {
            super.update();
        }

        return this;
    }

    public void validateNotDeleted() {
        if (this.isDeleted()) throw new IllegalStateException("삭제된 메세지입니다.");
    }

    @Override
    public String toString() {
        return "id: %s, 보낸 사람 (id): %s, 내용: %s, 채널 (id): %s".formatted(
                this.getId().toString().substring(0, 4),
                this.sentFromId.toString().substring(0, 4),
                this.content,
                this.channelId.toString().substring(0, 4));
    }
}
