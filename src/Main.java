import config.AppConfig;
import controller.UserController;
import repository.UserRepository;

public class Main {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();

        UserController userController = appConfig.userController();

        UserRepository userRepository = appConfig.userRepository();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\n프로그램을 종료하며 데이터를 저장합니다...");
            userRepository.saveAll();
            System.out.println("데이터 저장이 완료되었습니다.");
        }));

        userController.run();
    }
}