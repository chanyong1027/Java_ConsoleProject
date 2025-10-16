package repository;

import domain.Song;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SongRepository {
    void load();
    Map<Long, Song> findAll();
    Optional<Song> findById(Long songId);
    List<Song> findByArtist(String artist);
    List<Song> findByTitle(String title);
    List<Song> findByGenre(String genre);
}
