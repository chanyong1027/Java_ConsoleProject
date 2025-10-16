package controller;

import domain.User;
import repository.PlayListRepository;
import service.SessionManager;
import service.UserService;
import ui.ConsoleView;

public class UserController {
    private final UserService userService;
    private final ConsoleView consoleView;
    private final SessionManager sessionManager;

    public UserController(UserService userService, SessionManager sessionManager, ConsoleView consoleView) {
        this.userService = userService;
        this.consoleView = consoleView;
        this.sessionManager = sessionManager;
    }

    public void run() {
        while (true) {
            if (sessionManager.isLoggedIn()) {
                runUserMenu(); // 로그인 상태일 때
            } else {
                runMainMenu(); // 로그아웃 상태일 때
            }
        }
    }

    public void runMainMenu(){
            try{
                int choice = consoleView.displayMainMenu();
                switch (choice){
                    case 1 -> handleSignup();
                    case 2 -> handleLogin();
                    case 0 -> {
                        consoleView.displayMessage("프로그램을 종료합니다.");
                        System.exit(0);
                    }
                    default -> consoleView.displayMessage("잘못된 입력입니다. 다시 선택해주세요.");
                }
            } catch (NumberFormatException e) {
                consoleView.displayError("메뉴는 숫자로 입력해주세요.");
            }
    }

    private void runUserMenu() {
        // 현재 로그인한 유저의 이름을 가져와 메뉴에 표시
        sessionManager.getCurrentUser().ifPresent(user -> {
            String username = user.getUserName();
            try {
                int choice = consoleView.displayUserMenu(username);
                switch (choice) {
                    case 1 -> consoleView.displayMessage("플레이리스트기능");
                    case 2 -> consoleView.displayMessage("노래 추가 기능을 실행합니다.");
                    case 3 -> handleLogout();
                    case 4 -> handleDeleteAccount();
                    case 0 -> {
                        consoleView.displayMessage("프로그램을 종료합니다.");
                        System.exit(0);
                    }
                    default -> consoleView.displayError("잘못된 메뉴 선택입니다.");
                }
            } catch (NumberFormatException e) {
                consoleView.displayError("숫자로 메뉴를 입력해주세요.");
            }
        });
    }

    private void handleSignup(){
        String username = consoleView.getUserNameInput();
        String password = consoleView.getPasswordInput();

        userService.signUp(username, password)
                .ifPresentOrElse(
                        user -> consoleView.displayMessage(user.getUserName() + "님 회원가입을 환영합니다,"),
                        () -> consoleView.displayError("이미 존재하는 사용자명입니다.")
                );
    }

    private void handleLogin(){
        if (sessionManager.isLoggedIn()) {
            consoleView.displayError("이미 로그인 상태입니다.");
            return;
        }

        String username = consoleView.getUserNameInput();
        String password = consoleView.getPasswordInput();

        userService.login(username, password)
                .ifPresentOrElse(
                        user -> {
                            sessionManager.login(user);
                            consoleView.displayMessage("로그인 성공: " + user.getUserName());
                        },
                        () -> consoleView.displayError("사용자명 또는 비밀번호가 일치하지 않습니다.")
                );

    }

    private void handleLogout(){
        sessionManager.getCurrentUser()
                .ifPresentOrElse(
                        user -> {
                            sessionManager.logout();
                            consoleView.displayMessage("로그아웃 성공: " + user.getUserName());
                        },
                        () -> consoleView.displayError("로그인 상태가 아닙니다.")
                );
    }

    private void handleDeleteAccount() {
        if(consoleView.confirmDelete()){
            sessionManager.getCurrentUser().ifPresent(user -> {
                boolean isDeleted = userService.deleteUser(user.getId());

                if (isDeleted) {
                    sessionManager.logout();
                    consoleView.displayMessage(user.getUserName() + "님, 회원 탈퇴가 완료되었습니다.");
                }
                else {
                    consoleView.displayError("회원 탈퇴 중 오류가 발생했습니다.");
                }
            });
        }
        else {
            consoleView.displayMessage("회원 탈퇴가 취소되었습니다.");
        }
    }
}
