package musicapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Playlist {
    private ArrayList<Music> playlist = new ArrayList<>();
    private User owner;
    private String title;

    public Playlist(User owner, String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (owner == null) {
            throw new IllegalArgumentException("Owner cannot be null");
        }

        this.title = title;
        this.owner = owner;
    }

    public void editTitle(String password, String newTitle) {
        if (!authenticate(password)) {
            throw new InvalidOperationException("Invalid password");
        }
        if (newTitle == null || newTitle.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }

        this.title = newTitle;
    }

    public void addMusic(String password, Music music) {
        if (!authenticate(password)) {
            throw new InvalidOperationException("Invalid password");
        }
        if (music == null) {
            throw new IllegalArgumentException("Music cannot be null");
        }
        if (playlist.contains(music)) {
            throw new InvalidOperationException("Music already exists in playlist");
        }

        playlist.add(music);
    }

    public void removeMusic(String password, Music music) {
        if (!authenticate(password)) {
            throw new InvalidOperationException("Invalid password");
        }
        if (music == null) {
            throw new IllegalArgumentException("Music cannot be null");
        }
        if (!playlist.contains(music)) {
            throw new InvalidOperationException("Music not found in playlist");
        }

        playlist.remove(music);
    }

    public ArrayList<Music> searchInPlaylist(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }

        ArrayList<Music> result = new ArrayList<>();
        for (Music music : playlist) {
            if (music.getTitle().equalsIgnoreCase(title)) {
                result.add(music);
            }
        }
        return result;
    }

    public Music searchInPlaylist(String title, User singer) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (singer == null) {
            throw new IllegalArgumentException("Singer cannot be null");
        }

        for (Music music : playlist) {
            if (music.getTitle().equalsIgnoreCase(title) &&
                    music.getSinger().equals(singer)) {
                return music;
            }
        }
        return null;
    }

    public void playPlaylist() {
        Scanner scanner = new Scanner(System.in);
        int currentTrack = 1;

        for (Music music : playlist) {
            System.out.println("Now playing track " + currentTrack + " of " + playlist.size());
            music.play();

            while (true) {
                System.out.print("Enter 'next' to continue or 'stop' to quit: ");
                String input = scanner.nextLine().trim();

                if ("next".equalsIgnoreCase(input)) {
                    currentTrack++;
                    break;
                }
                else if ("stop".equalsIgnoreCase(input)) {
                    System.out.println("\nPlayback stopped after " + (currentTrack) + " tracks.");
                    return;
                }
                else {
                    System.out.println("Invalid command! Please enter 'next' or 'stop'.");
                }
            }
        }

        System.out.println("Playlist completed! " + playlist.size() + " tracks played.");
    }

    public void shufflePlaylist() {
        Collections.shuffle(playlist);
        playPlaylist();
    }

    private boolean authenticate(String password) {
        return owner != null && owner.getPassword().equals(password);
    }

    public String getTitle() {
        return title;
    }

    public User getOwner() {
        return owner;
    }

}