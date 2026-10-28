package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.Dto.User.*;
import com.sprint.mission.discodeit.entity.BinaryContent;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.entity.UserStatus;
import com.sprint.mission.discodeit.repository.BinaryContentRepository;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.repository.UserStatusRepository;
import com.sprint.mission.discodeit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@RequiredArgsConstructor
@Service
public class BasicUserService implements UserService {
    private final UserRepository userRepository;
    private final ChannelRepository channelRepository;
    private final BinaryContentRepository binaryContentRepository;
    private final UserStatusRepository userStatusRepository;

    @Override
    public UserCreateResponse create(UserCreateRequest userCreateRequest, UserProfileImageCreateRequest userProfileImageCreateRequest) {
        // TODO: 비밀번호 암호화

        String name = userCreateRequest.name();
        String email = userCreateRequest.email();

        this.userRepository.findByName(name).ifPresent((existingUser) -> {
            throw new IllegalArgumentException("이미 같은 이름의 유저가 존재합니다. 이름: %s".formatted(existingUser.getName()));
        });
        this.userRepository.findByEmail(email).ifPresent((existingUser) -> {
            throw new IllegalArgumentException("이미 같은 이메일의 유저가 존재합니다. 이메일: %s".formatted(existingUser.getEmail()));
        });

        User user = new User(name, email, userCreateRequest.password());

        this.userStatusRepository.save(new UserStatus(user.getId(), Instant.now()));

        UUID profileId = null;
        if (userProfileImageCreateRequest != null) {
            String fileName = userProfileImageCreateRequest.fileName();
            String extension = userProfileImageCreateRequest.extension();
            byte[] content = userProfileImageCreateRequest.content();

            BinaryContent binaryContent = new BinaryContent(fileName, extension, content);
            profileId = binaryContent.getId();
            user.assignProfileImage(profileId);
            this.binaryContentRepository.save(binaryContent);
        }
        this.userRepository.save(user);

        return UserCreateResponse.of(user, profileId);
    }

    @Override
    public UserFindResponse findById(UUID id) {
        User user = this.userRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("유저가 존재하지 않습니다."));
        UserStatus userStatus = this.userStatusRepository.findByUserId(id).orElseThrow(
                () -> new NoSuchElementException("유저의 접속 시간 정보가 존재하지 않습니다."));
        return UserFindResponse.of(user, userStatus.isUserOnline());
    }

    @Override
    public UserFindAllResponse findAll() {
        List<User> users = this.userRepository.findAll();
        List<UserFindResponse> userFindResponses = users
                .stream()
                .map(user -> {
                    UserStatus userStatus = this.userStatusRepository.findByUserId(user.getId()).orElseThrow(
                            () -> new NoSuchElementException("유저의 접속 시간 정보가 존재하지 않습니다"));
                    return UserFindResponse.of(user, userStatus.isUserOnline());
                })
                .toList();
        return UserFindAllResponse.from(userFindResponses);
    }

    @Override
    public UserUpdateResponse update(UserUpdateRequest userUpdateRequest, UserProfileImageUpdateRequest userProfileImageUpdateRequest) {
        UUID userId = userUpdateRequest.userId();
        User user = this.userRepository.findById(userId).orElseThrow(
                () -> new NoSuchElementException("수정할 유저가 존재하지 않습니다."));
        user.validateNotDeleted();

        String newName = userUpdateRequest.name();
        String newEmail = userUpdateRequest.email();
        String newPassword = userUpdateRequest.password();

        user.update(newName, newEmail, newPassword);

        UUID profileId = null;
        if (userProfileImageUpdateRequest != null) {
            String fileName = userProfileImageUpdateRequest.fileName();
            String extension = userProfileImageUpdateRequest.extension();
            byte[] content = userProfileImageUpdateRequest.content();
            profileId = this.binaryContentRepository.findByUserId(userId).orElseThrow(
                    () -> new NoSuchElementException("수정할 유저의 프로필 이미지가 존재하지 않습니다.")).getId();

            BinaryContent binaryContent = new BinaryContent(fileName, extension, content);
            user.assignProfileImage(profileId);
            this.binaryContentRepository.update(profileId, binaryContent);
        }
        this.userRepository.update(userId, user);

        return UserUpdateResponse.of(user, profileId);
    }

    @Override
    public void delete(UUID id) {
        User user = this.userRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("삭제할 유저가 존재하지 않습니다."));
        user.validateNotDeleted();

        Set<UUID> channels = new HashSet<>(user.getChannels());
        channels.forEach(channelId -> this.channelRepository.findById(channelId).ifPresent(channel -> {
            channel.removeUser(id);
            this.channelRepository.update(channelId, channel);
        }));

        this.binaryContentRepository.findByUserId(id).ifPresent(binaryContent -> this.binaryContentRepository.delete(binaryContent.getId()));
        user.assignProfileImage(null);

        UserStatus userStatus = this.userStatusRepository.findByUserId(id).orElseThrow(
                () -> new NoSuchElementException("삭제할 유저의 접속 기록이 존재하지 않습니다."));
        this.userStatusRepository.delete(userStatus.getId());

        this.userRepository.delete(id);
    }
}
