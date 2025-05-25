package model;

import java.util.ArrayList;

public class User {
    private String username;
    private ArrayList<String> posts;
    private ArrayList<String> friends;

    public User(String username) {
        this.username = username;
        this.posts = new ArrayList<>();
        this.friends = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<String> getPosts() {
        return posts;
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

    public void addPost(String content) {
        posts.add(content);
        System.out.println(username + " posted: " + content);
    }

    public void addFriend(String friendUsername) {
        if (!friends.contains(friendUsername)) {
            friends.add(friendUsername);
            System.out.println(username + " and " + friendUsername + " are now friends!");
        } else {
            System.out.println(friendUsername + " is already your friend.");
        }
    }
}