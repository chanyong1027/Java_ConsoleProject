package domain;

public class Song {
    private Long songId;
    private final String title;
    private final String artist;
    private final String genre;
    private final String album;
    private final String year;
    private final String duration;

    public Song(String title, String artist, String genre, String album, String year, String duration) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.album = album;
        this.year = year;
        this.duration = duration;
    }

    public Long getSongId() {
        return songId;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public String getAlbum() {
        return album;
    }

    public String getYear() {
        return year;
    }

    public String getDuration() {
        return duration;
    }

    public static Song fromString(String line) {
        String[] parts = line.split(",", 7);
        if (parts.length < 7) throw new IllegalArgumentException("잘못된 Song 데이터 형식: " + line);

        Song song = new Song(parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
        song.setId(Long.parseLong(parts[0])); // 파일에서 읽은 id 설정
        return song;
    }

    public void setId(Long id) {
        this.songId = id;
    }
}
