package musicapp;

import java.util.ArrayList;
import java.util.List;

public class Music {
    private String title;
    private User singer;
    private int numberOfStream;
    private static ArrayList<Music> allMusics = new ArrayList<>();

    public Music(String title, User singer) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (singer == null) {
            throw new IllegalArgumentException("Singer cannot be null");
        }

        this.title = title;
        this.singer = singer;
        this.numberOfStream = 0;
        allMusics.add(this);
    }

    public void play() {
        if (allMusics.contains(this)) {
        System.out.println("Now Playing: " + this.title + " by " + this.singer.getUserName());
        this.numberOfStream++;
        }

        else {
            throw new InvalidOperationException("Sorry, " + this.title + " by " + this.singer + " isn't available in our library yet.");
        }

    }

    public static ArrayList<Music> search(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }

        ArrayList<Music> relatedMusic = new ArrayList<>();

        for (Music music : allMusics) {
            if (music.title.equals(title)) {
                relatedMusic.add(music);
            }
        }

        return relatedMusic;// dar main check beshe;
    }

    public static Music search(String title, User singer) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (singer == null) {
            throw new IllegalArgumentException("Singer cannot be null");
        }

        for (Music music : allMusics) {
            if (music.title.equalsIgnoreCase(title) && music.singer.equals(singer)) {
                return music;
            }
        }
        return null;
    }

    public String getTitle() {
        return title;
    }

    public User getSinger() {
        return singer;
    }

    public int getNumberOfStream() {
        return numberOfStream;
    }

    public static List<Music> getAllMusics() {
        return new ArrayList<>(allMusics);
    }
}