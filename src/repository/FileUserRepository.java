package repository;

import domain.User;
import utils.FileUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class FileUserRepository implements UserRepository {
    private final Map<Long, User> store = new HashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);
    private final String filePath;

    public FileUserRepository(String filePath) {
        this.filePath = filePath;
        load();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return store.values().stream()
                .filter(user -> user.getUserName().equals(username))
                .findFirst();
    }

    @Override
    public void save(User user) {
        if (user.getId() == null) {
            user.setId(sequence.incrementAndGet());
        }
        store.put(user.getId(), user);
        saveAll();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
        saveAll();
    }

   @Override
   public void load() {
        store.clear();
        List<String> lines = FileUtils.readLines(filePath);
        long maxId = 0;
        for (String line : lines) {
           try {
               User user = User.fromString(line);
               store.put(user.getId(), user);
               if (user.getId() > maxId) {
                   maxId = user.getId();
               }
           } catch (Exception e) {
               System.out.println("⚠️ 잘못된 유저 데이터 로드: " + line);
           }
       }
       sequence.set(maxId);
   }

    @Override
    public void saveAll() {
        List<String> lines = store.values().stream()
                .map(User::toString)
                .toList();
        FileUtils.writeLines(filePath, lines);
    }
}
