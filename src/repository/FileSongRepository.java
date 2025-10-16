package repository;

import domain.Song;
import utils.FileUtils;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class FileSongRepository implements SongRepository {
    private final Map<Long, Song> storeSong = new HashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);
    private final String filePath;

    public FileSongRepository(String filePath) {
        this.filePath = filePath;
        load();
    }

    @Override
    public void load(){
        storeSong.clear();
        List<String> lines = FileUtils.readLines(filePath);

        long maxId = 0;
        for (String line : lines) {
            try {
                Song song = Song.fromString(line);
                storeSong.put(song.getSongId(), song);
                if (song.getSongId() > maxId) {
                    maxId = song.getSongId();
                }
            } catch (Exception e) {
                System.out.println("⚠️ 잘못된 Song 데이터 로드: " + line);
            }
        }
        sequence.set(maxId);
    }

    @Override
    public Map<Long, Song> findAll() {
        return new HashMap<>(storeSong);
    }

    @Override
    public Optional<Song> findById(Long songId) {
        return Optional.ofNullable(storeSong.get(songId));
    }

   @Override
   public List<Song> findByTitle(String title) {
       return storeSong.values().stream().filter(song -> song.getTitle().toLowerCase()
                       .contains(title.toLowerCase())).collect(Collectors.toList());
   }

    @Override
    public List<Song> findByArtist(String artist) {
        return storeSong.values().stream().filter(song -> song.getArtist().
                contains(artist)).collect(Collectors.toList());
    }

    @Override
    public List<Song> findByGenre(String genre) {
        return storeSong.values().stream().filter(song -> song.getGenre()
                .contains(genre)).collect(Collectors.toList());
    }
}
