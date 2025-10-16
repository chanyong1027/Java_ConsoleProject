package service;

import domain.Song;
import repository.SongRepository;

public class SongService {
    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public void getBySongId(Long id) {
        songRepository.findById(id);
    }

}
