package util;

import model.User;

import java.io.*;
import java.util.*;

public class FileUtil {
    private static final String USERS_FILE = "data/users.txt";
    private static final String POSTS_FILE = "data/posts.txt";
    private static final String FRIENDS_FILE = "data/friends.txt";

    public static void init() {
        try {
            new File("data").mkdirs();
            new File(USERS_FILE).createNewFile();
            new File(POSTS_FILE).createNewFile();
            new File(FRIENDS_FILE).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveUser(User user) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USERS_FILE, true))) {
            bw.write(user.getUsername());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void savePost(String username, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(POSTS_FILE, true))) {
            bw.write(username + ":" + content);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveFriend(String username, String friendName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FRIENDS_FILE, true))) {
            bw.write(username + ":" + friendName);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, User> loadUsers() {
        Map<String, User> users = new HashMap<>();

        try (BufferedReader userReader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = userReader.readLine()) != null) {
                users.put(line, new User(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader postReader = new BufferedReader(new FileReader(POSTS_FILE))) {
            String line;
            while ((line = postReader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                User user = users.get(parts[0]);
                if (user != null) user.getPosts().add(parts[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader friendReader = new BufferedReader(new FileReader(FRIENDS_FILE))) {
            String line;
            while ((line = friendReader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                User user = users.get(parts[0]);
                if (user != null) user.getFriends().add(parts[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }
}