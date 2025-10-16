package repository;

import domain.PlayList;

import java.util.Map;

public interface PlayListRepository {
    void load();
    void addPlayList(PlayList playlist);
    void removePlayList(PlayList playlist);
    Map<Long, PlayList> findAll();
}
