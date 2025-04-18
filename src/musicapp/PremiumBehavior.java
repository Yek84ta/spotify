package musicapp;

import java.util.ArrayList;

public class PremiumBehavior implements UserBehavior {
    private int month;

    public PremiumBehavior(int month) {
        if (month <= 0) {
            throw new IllegalArgumentException("Month must be positive");
        }
        this.month = month;
    }

    public int getRemainingMonths() {
        return month;
    }


    @Override
    public void createPlaylist(String title, User owner) {
        Playlist playlist = new Playlist(owner, title);
        ArrayList<Playlist>playlists = owner.getPlaylists();
        playlists.add(playlist);
    }

    @Override
    public void playMusic(Music music) {
        music.play();
    }

    @Override
    public void buyPremium(User owner, int month) {
        if (owner == null) {
            throw new IllegalArgumentException("Owner cannot be null");
        }

        if (month <= 0) {
            throw new IllegalArgumentException("Month must be positive");
        }

        this.month += month;
    }
}
