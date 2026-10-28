package com.sprint.mission.discodeit.entity;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Getter
public abstract class BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final UUID id;
    private final Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;
    private EntityStatus status;

    protected BaseEntity() {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        this.updatedAt = this.createdAt;
        this.status = EntityStatus.ACTIVE;
    }

    protected void update() {
        this.updatedAt = Instant.now();
    }

    public void softDelete() {
        this.status = EntityStatus.DELETED;
        this.deletedAt = Instant.now();
    }

    public boolean isDeleted() {
        return this.status == EntityStatus.DELETED;
    }

    public enum EntityStatus {
        ACTIVE("활동 중"),
        DELETED("삭제됨");

        private final String value;

        EntityStatus(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }
}
