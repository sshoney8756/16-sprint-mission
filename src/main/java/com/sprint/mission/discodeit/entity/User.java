package com.sprint.mission.discodeit.entity;

import java.util.*;
import java.util.stream.Collectors;

public class User extends BaseEntity {
    private final Set<UUID> channels;
    private String name;
    private String email;
    private String password;

    public User(String name, String email, String password) {
        super();
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
            super.update();
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
        super.update();
    }

    public void removeChannel(UUID channelId) {
        this.channels.remove(channelId);
        super.update();
    }

    public String getName() {
        return this.name;
    }

    public Set<UUID> getChannels() {
        return this.channels;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
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
