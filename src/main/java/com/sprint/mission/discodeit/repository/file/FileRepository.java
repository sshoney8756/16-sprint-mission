package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.entity.BaseEntity;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public abstract class FileRepository<T extends BaseEntity> {
    protected final String filePath;
    protected final Map<UUID, T> dataStorage;

    @PostConstruct
    public void init() {
        if (this.checkIfFileExists(filePath)) {
            System.out.printf("기존 파일을 찾았습니다: %s, 저장된 데이터를 사용합니다.\n".formatted(filePath));
            return;
        }
        System.out.printf("기존 파일이 없습니다. 새 데이터를 저장합니다: %s\n".formatted(filePath));
        this.writeFile(dataStorage, filePath);
    }

    public Map<UUID, T> readFile(String path) {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(path))) {
            @SuppressWarnings("unchecked")
            Map<UUID, T> data = (Map<UUID, T>) ois.readObject();
            return data;
        } catch (IOException e) {
            throw new UncheckedIOException("파일 입출력 도중 예외가 발생했습니다.", e);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError("직렬화된 객체의 클래스를 찾을 수 없습니다.");
        }
    }

    public void writeFile(Map<UUID, T> data, String path) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(data);
        } catch (IOException e) {
            throw new UncheckedIOException("파일 입출력 도중 예외가 발생했습니다.", e);
        }
    }

    public boolean checkIfFileExists(String pathStr) {
        Path path = Paths.get(pathStr);
        return Files.exists(path) && Files.isRegularFile(path);
    }
}
