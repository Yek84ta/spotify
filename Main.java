import musicapp.*;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Music App!");

        User artist1 = new User("TaylorSwift", "password123");
        User artist2 = new User("EdSheeran", "red12345");
        User regularUser = new User("musicFan", "ilovemusic8");
        User premiumUser = new User("vipListener", "premium999");
        premiumUser.buyPremium(12);

        Music song1 = new Music("Love Story", artist1);
        Music song2 = new Music("Azizam", artist2);
        Music song3 = new Music("Blank Space", artist1);

        System.out.println("--- Regular User Demo ---");
        try {
            System.out.println("Regular user trying to create playlist:");
            regularUser.createPlaylist("My Favorites", regularUser);
        } catch (InvalidOperationException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Regular user playing songs (limited to 5 plays):");
        for (int i = 0; i < 6; i++) {
            try {
                System.out.print("Play attempt " + (i+1) + ": ");
                regularUser.playMusic(song1);
            } catch (InvalidOperationException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("--- Premium User Demo ---");
        System.out.println("Premium user creating playlist:");
        premiumUser.createPlaylist("Premium Mix", premiumUser);

        Playlist premiumPlaylist = User.getPlaylists().getLast();

        premiumPlaylist.addMusic("premium999", song1);
        premiumPlaylist.addMusic("premium999", song2);
        premiumPlaylist.addMusic("premium999", song3);

        System.out.println("Playing playlist:");
        premiumPlaylist.playPlaylist();

        System.out.println("--- Upgrade to Premium Demo ---");
        System.out.println("Regular user upgrading to premium:");
        regularUser.buyPremium(3);

        System.out.println("Now creating playlist as premium user:");
        regularUser.createPlaylist("Upgraded User's Playlist", regularUser);

        System.out.println("--- Search Demo ---");
        AbstractList<Music> swiftSongs = Music.search("Love Story");
        if (!swiftSongs.isEmpty()) {
            System.out.println("Found song: " + swiftSongs.get(0).getTitle() +
                    " by " + swiftSongs.get(0).getSinger().getUserName());
        }

        System.out.println("--- Social Features Demo ---");
        regularUser.follow(artist1);
        System.out.println(regularUser.getUserName() + " is now following " +
                artist1.getUserName());
        System.out.println(artist1.getUserName() + "'s followers: " +
                artist1.getFollowerList().size());

        System.out.println("--- Application Statistics ---");
        System.out.println("Total users: " + User.getAllUsers().size());
        System.out.println("Total songs: " + Music.getAllMusics().size());
        System.out.println("Streams for 'Love Story': " + song1.getNumberOfStream());

        scanner.close();
    }
}