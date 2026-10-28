package com.sprint.mission.discodeit.entity;

import lombok.Getter;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Getter
public class User extends BaseEntity implements Updatable {
    private final Set<UUID> channels;
    private UUID profileId;
    private String name;
    private String email;
    private String password;
    private Instant updatedAt;

    public User(String name, String email, String password) {
        super();
        this.updatedAt = this.getCreatedAt();
        this.name = name;
        this.email = email;
        this.password = password;
        this.channels = new HashSet<>();
    }

    public User update(String newUsername, String newEmail, String newPassword) {
        boolean updated = false;
        if (newUsername != null && !newUsername.equals(this.name)) {
            this.name = newUsername;
            updated = true;
        }
        if (newEmail != null && !newEmail.equals(this.email)) {
            this.email = newEmail;
            updated = true;
        }
        if (newPassword != null && !newPassword.equals(this.password)) {
            this.password = newPassword;
            updated = true;
        }

        if (updated) {
            this.update();
        }

        return this;
    }

    public void validateNotDeleted() {
        if (this.isDeleted()) {
            throw new IllegalStateException("삭제된 유저입니다.");
        }
    }

    public void addChannel(UUID channelId) {
        this.channels.add(channelId);
        this.update();
    }

    public void removeChannel(UUID channelId) {
        this.channels.remove(channelId);
        this.update();
    }

    @Override
    public void update() {
        this.updatedAt = Instant.now();
    }

    @Override
    public String toString() {
        String str =
                this.channels.stream().map(uuid -> uuid.toString().substring(0, 4)).collect(Collectors.joining(", "));
        return "이름: %s, id: %s, 가입 채널: [ %s ]".formatted(
                this.name,
                this.getId().toString().substring(0, 4),
                str);
    }
}
