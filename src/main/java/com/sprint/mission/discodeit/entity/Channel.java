package com.sprint.mission.discodeit.entity;

import java.util.*;
import java.util.stream.Collectors;

public class Channel extends BaseEntity {
    private final Set<UUID> users;
    private final ChannelType type;
    private String name;
    private String description;

    public Channel(String name, String description, ChannelType type) {
        super();
        this.name = name;
        this.description = description;
        this.type = type;
        this.users = new HashSet<>();
    }

    public Channel update(String newName, String newDescription) {
        boolean updated = false;
        if (newName != null && !newName.equals(this.name)) {
            this.name = newName;
            updated = true;
        }

        if (newDescription != null && !newDescription.equals(this.description)) {
            this.description = newDescription;
            updated = true;
        }

        if (updated) {
            super.update();
        }

        return this;
    }

    public void validateNotDeleted() {
        if (this.isDeleted()) throw new IllegalStateException("삭제된 채널입니다.");
    }

    public void addUser(UUID userId) {
        this.users.add(userId);
        super.update();
    }

    public void removeUser(UUID userId) {
        this.users.remove(userId);
        super.update();
    }

    public String getName() {
        return this.name;
    }

    public Set<UUID> getUsers() {
        return this.users;
    }

    public ChannelType getType() {
        return this.type;
    }

    @Override
    public String toString() {
        String userStr = this.users
                .stream()
                .map(uuid -> uuid.toString().substring(0, 4))
                .collect(Collectors.joining(", "));
        return "이름: %s, id: %s, 가입 유저 (id): [ %s ]".formatted(
                this.name,
                this.getId().toString().substring(0, 4), userStr);
    }
}
