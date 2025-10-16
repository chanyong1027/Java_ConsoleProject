package ui;

import domain.PlayList;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleView {
    private final Scanner scanner;

    public ConsoleView() {
        scanner = new Scanner(System.in);
    }

    public int displayMainMenu() {
        String menu = """
            
            ===== 음악 취향 분석기 =====
            1. 회원가입
            2. 로그인
            0. 종료
            메뉴를 선택하세요 > """;

        System.out.print(menu); // print로 한 번에 출력
        return Integer.parseInt(scanner.nextLine());
    }

    public int displayUserMenu(String username) {
        String menu = """

                ===== [%s님 환영합니다] =====
                1. 내 플레이리스트 조회
                2. 플레이리스트에 노래 추가
                3. 로그아웃
                4. 회원 탈퇴
                0. 종료
                메뉴를 선택하세요 > """.formatted(username);
        System.out.print(menu);
        return Integer.parseInt(scanner.nextLine());
    }

    public int displayPlaylistMenu(String username, Map<Long, PlayList> playlists) {
        StringBuilder sb = new StringBuilder();

        // 상단 UI 디자인
        sb.append("==================================================\n");
        sb.append("      🎵 ").append(username).append("님의 플레이리스트 🎵\n");
        sb.append("==================================================\n\n");

        // 플레이리스트 내용 표시
        if (playlists.isEmpty()) {
            sb.append("          텅... ✨\n");
            sb.append("          플레이리스트가 비어있습니다.\n\n");
        } else {
            for (int i = 0; i < playlists.size(); i++) {
                // 한글 정렬을 위해 String.format 사용
                String formattedSong = String.format("    %2d. %s", (i + 1), playlists.get(i));
                sb.append(formattedSong).append("\n");
            }
            sb.append("\n");
        }

        // 하단 메뉴 옵션 UI
        sb.append("--------------------------------------------------\n");
        sb.append("    1. 플레이리스트 추가      2. 플레이리스트 삭제\n");
        sb.append("    3. 돌아가기\n");
        sb.append("--------------------------------------------------\n");
        sb.append("  메뉴를 선택하세요 >> ");

        // 완성된 UI를 한 번에 출력
        System.out.print(sb.toString());
        System.out.print(sb);
        return Integer.parseInt(scanner.nextLine());
    }

    public String getUserNameInput(){
        System.out.print("사용자명(ID)을 입력하세요: ");
        return scanner.nextLine();
    }

    public String getPasswordInput(){
        System.out.print("비밀번호를 입력하세요: ");
        return scanner.nextLine();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayError(String message) {
        System.out.println("[ERROR] " + message);
    }

    public boolean confirmDelete() {
        System.out.print("정말 탈퇴하시겠습니까? 모든 정보가 삭제됩니다. (Y/N) > ");
        String answer = scanner.nextLine();
        return answer.equalsIgnoreCase("Y");
    }

}
