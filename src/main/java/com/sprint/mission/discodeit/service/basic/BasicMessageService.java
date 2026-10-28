package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.MessageRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BasicMessageService implements MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ChannelRepository channelRepository;

    @Override
    public Message create(UUID sentFromId, String content, UUID channelId) {
        // 유저와 채널 존재 여부 확인
        User sentFrom = this.userRepository.findById(sentFromId)
                .orElseThrow(() -> new NoSuchElementException("메세지를 보내는 유저가 존재하지 않습니다."));
        Channel channel = this.channelRepository.findById(channelId)
                .orElseThrow(() -> new NoSuchElementException("메세지를 보낼 채널이 존재하지 않습니다."));
        sentFrom.validateNotDeleted();
        channel.validateNotDeleted();

        // 채널 가입 여부 확인
        channel.getUsers()
                .stream()
                .filter(userId -> this.userRepository.findById(userId).isPresent())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("채널에 가입되지 않은 유저입니다."));

        Message message = new Message(sentFromId, content, channelId);
        this.messageRepository.save(message);

        return message;
    }

    @Override
    public Message update(UUID id, String newContent) {
        Message message = this.messageRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("수정할 메세지가 존재하지 않습니다."));
        message.validateNotDeleted();

        message.update(newContent);
        return this.messageRepository.update(id, message);
    }

    @Override
    public Optional<Message> findById(UUID id) {
        return this.messageRepository.findById(id);
    }

    @Override
    public List<Message> findAll() {
        return this.messageRepository.findAll();
    }

    @Override
    public void delete(UUID id) {
        Message message = this.messageRepository.findById(id).orElseThrow(() -> new NoSuchElementException("삭제할 메세지가 존재하지 " +
                "않습니다."));
        message.validateNotDeleted();

        this.messageRepository.delete(id);
    }
}
