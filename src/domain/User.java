package domain;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Long id; //final 왜쓰는지 검색해보기
    private final String username;
    private final String userPassword;
    private List<PlayList> myPlaylists = new ArrayList<PlayList>();

    public User(String userName, String userPassword) {
        this.username = userName;
        this.userPassword = userPassword;
    }


    public Long getId() {
        return id;
    }
    public String getUserName() {return username;}
    public String getUserPassword() {return userPassword;}

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id + "," + username + "," + userPassword;
    }

    public static User fromString(String line) {
        String[] parts = line.split(",", 3);
        if (parts.length < 3) throw new IllegalArgumentException("잘못된 유저 데이터 형식: " + line);

        User user = new User(parts[1], parts[2]);
        user.setId(Long.parseLong(parts[0])); // 파일에서 읽은 id 설정
        return user;
    }
}
