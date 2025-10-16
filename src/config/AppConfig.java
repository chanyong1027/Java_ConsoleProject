package config;

import controller.UserController;
import domain.PlayList;
import repository.*;
import service.SessionManager;
import service.SongService;
import service.UserService;
import ui.ConsoleView;

public class AppConfig {
    private final String DATA_DIRECTORY = "data/";
    private final String USER_FILE_PATH = DATA_DIRECTORY + "users.txt";
    private final String SONGS_FILE_PATH = DATA_DIRECTORY + "songs.txt";

    private final UserRepository userRepository = new FileUserRepository(USER_FILE_PATH);
    private final UserService userService = new UserService(userRepository);
    private final SongRepository songRepository = new FileSongRepository(SONGS_FILE_PATH);
    private final SongService songService = new SongService(songRepository);
    private final PlayListRepository playListRepository = new FilePlayListRepository(DATA_DIRECTORY);
    private final ConsoleView consoleView = new ConsoleView();
    private final SessionManager sessionManager = new SessionManager();
    private final UserController userController =
            new UserController(userService, sessionManager, consoleView);

    public UserRepository userRepository(){
        return userRepository;
    }

    public SongRepository songRepository(){return songRepository;}

    public UserService userService(){
        return userService;
    }

    public ConsoleView consoleView(){
        return consoleView;
    }

    public SessionManager sessionManager(){
        return sessionManager;
    }

    public UserController userController(){
        return userController;
    }
}
