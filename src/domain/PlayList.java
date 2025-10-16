package domain;

import java.util.List;

public class PlayList {
    private Long playlistId;
    private String playlistName;
    private String playlistDescription;
    private List<Song> songs;


    public PlayList(String playlistName, String playlistDescription) {
        this.playlistName = playlistName;
        this.playlistDescription = playlistDescription;
    }

    public String getPlaylistName() {
        return playlistName;
    }
    public String getPlaylistDescription() {
        return playlistDescription;
    }
    public Long getPlaylistId() {return playlistId;}

    public static PlayList fromString(String line) {
        String[] parts = line.split(",", 3);
        if (parts.length < 3) throw new IllegalArgumentException("잘못된 Playlist 데이터 형식: " + line);

        PlayList playlist = new PlayList(parts[1], parts[2]);
        playlist.setId(Long.parseLong(parts[0]));
        return playlist;
    }

    public void setId(Long id) {
        this.playlistId = id;
    }
}
