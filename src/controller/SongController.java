package controller;

import service.SessionManager;
import service.SongService;
import ui.ConsoleView;

public class SongController {
    private final SongService songService;
    private final ConsoleView consoleView;
    private final SessionManager sessionManager;

    public SongController(SongService songService, SessionManager sessionManager, ConsoleView consoleView) {
        this.songService = songService;
        this.sessionManager = sessionManager;
        this.consoleView = consoleView;
    }

    public void run(){

    }
}
