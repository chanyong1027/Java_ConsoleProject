package repository;

import domain.PlayList;
import utils.FileUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class FilePlayListRepository implements PlayListRepository {
    private final Map<Long, PlayList> storePlaylist = new HashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);
    private final String filePath;

    public FilePlayListRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void load(){
        storePlaylist.clear();
        List<String> lines = FileUtils.readLines(filePath);

        long maxId = 0;
        for (String line : lines) {
            try {
                PlayList playlist = PlayList.fromString(line);
                storePlaylist.put(playlist.getPlaylistId(), playlist);
                if (playlist.getPlaylistId() > maxId) {
                    maxId = playlist.getPlaylistId();
                }
            } catch (Exception e) {
                System.out.println("⚠️ 잘못된 Playlist 데이터 로드: " + line);
            }
        }
        sequence.set(maxId);
    }

    @Override
    public void addPlayList(PlayList playlist) {
        if(playlist == null) {
            playlist.setId(sequence.getAndIncrement());
        }
        storePlaylist.put(playlist.getPlaylistId(), playlist);
    }

    @Override
    public void removePlayList(PlayList playlist) {

    }

    @Override
    public Map<Long, PlayList> findAll(){
        return new HashMap<>(storePlaylist);
    }
}
