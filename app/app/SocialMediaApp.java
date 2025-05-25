package app;

import service.UserService;
import util.FileUtil;

import java.util.Scanner;

public class SocialMediaApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileUtil.init();
        UserService service = new UserService();
        service.loadData();

        while (true) {
            System.out.println("\n \"INSTAGRAM\" - Social Media Platform ");
            System.out.println("1. Register User");
            System.out.println("2. Add Post");
            System.out.println("3. Add Friend");
            System.out.println("4. View Posts");
            System.out.println("5. View Friends");
            System.out.println("6. Exit");
            System.out.print("Select your choice ☺ : ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    service.registerUser(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Enter your username: ");
                    String postUser = scanner.nextLine();
                    System.out.print("Enter your post content: ");
                    service.addPost(postUser, scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Enter your username: ");
                    String user = scanner.nextLine();
                    System.out.print("Enter friend's username: ");
                    service.addFriend(user, scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Enter your username: ");
                    service.displayPosts(scanner.nextLine());
                    break;
                case 5:
                    System.out.print("Enter your username: ");
                    service.displayFriends(scanner.nextLine());
                    break;
                case 6:
                    System.out.println("Exiting the platform...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}