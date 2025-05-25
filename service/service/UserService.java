package service;

import model.User;
import util.FileUtil;

import java.util.*;

public class UserService {
    private Map<String, User> users = new HashMap<>();

    public void registerUser(String username) {
        if (!users.containsKey(username)) {
            User user = new User(username);
            users.put(username, user);
            FileUtil.saveUser(user);
            System.out.println(username + " has been registered.");
        } else {
            System.out.println("User already exists.");
        }
    }

    public void addPost(String username, String content) {
        User user = users.get(username);
        if (user != null) {
            user.addPost(content);
            FileUtil.savePost(username, content);
        } else {
            System.out.println("User not found.");
        }
    }

    public void addFriend(String username, String friendName) {
        User user = users.get(username);
        User friend = users.get(friendName);
        if (user != null && friend != null) {
            user.addFriend(friendName);
            friend.addFriend(username);
            FileUtil.saveFriend(username, friendName);
            FileUtil.saveFriend(friendName, username);
        } else {
            System.out.println("User or friend not found.");
        }
    }

    public void displayPosts(String username) {
        User user = users.get(username);
        if (user != null) {
            System.out.println("\n" + username + "'s Posts:");
            for (String post : user.getPosts()) {
                System.out.println(" = " + post);
            }
        } else {
            System.out.println("User not found.");
        }
    }

    public void displayFriends(String username) {
        User user = users.get(username);
        if (user != null) {
            System.out.println("\n" + username + "'s Friends:");
            for (String friend : user.getFriends()) {
                System.out.println("- " + friend);
            }
        } else {
            System.out.println("User not found.");
        }
    }

    public void loadData() {
        users = FileUtil.loadUsers();
    }
}