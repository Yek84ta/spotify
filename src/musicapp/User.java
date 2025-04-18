package musicapp;

import java.util.ArrayList;

public class User {
    private String userName;
    private String password;
    private ArrayList<User> followerList = new ArrayList<>();
    private ArrayList<User> followingList = new ArrayList<>();
    private UserBehavior behavior;
    private static ArrayList<Playlist> playlists = new ArrayList<>();
    private static ArrayList<User> allUsers = new ArrayList<>();

    public User(String userName, String password) {
        if (userName == null || userName.isEmpty()) {
            throw new InvalidOperationException("Username cannot be null or empty");
        }

        for (User user : allUsers) {
            if (user.userName.equals(userName)) {
                throw new InvalidOperationException("Username already exists");
            }
        }

        if (password == null || password.length() < 8) {
            throw new InvalidOperationException("Password must be at least 8 characters long");
        }

        this.userName = userName;
        this.password = password;
        this.behavior = new RegularBehavior();

        allUsers.add(this);
    }

    public void follow(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (this == user) {
            throw new InvalidOperationException("Cannot follow yourself");
        }
        if (followingList.contains(user)) {
            throw new InvalidOperationException("Already following this user");
        }

        followingList.add(user);
        user.followerList.add(this);
    }

    public void createPlaylist(String title, User owner) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (owner == null) {
            throw new IllegalArgumentException("Owner cannot be null");
        }
        this.behavior.createPlaylist(title, owner);
    }

    public void playMusic(Music music) {
        if (music == null) {
            throw new IllegalArgumentException("Music cannot be null");
        }
        this.behavior.playMusic(music);
    }

    public void buyPremium(int month) {
        this.behavior.buyPremium(this, month);
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<User> getFollowerList() {
        return new ArrayList<>(followerList);
    }

    public ArrayList<User> getFollowingList() {
        return new ArrayList<>(followingList);
    }

    public static ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public static ArrayList<User> getAllUsers() {
        return new ArrayList<>(allUsers);
    }

    public void setBehavior(UserBehavior behavior) {
        this.behavior = behavior;
    }
}