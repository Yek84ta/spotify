package musicapp;

public class RegularBehavior implements UserBehavior{
    private int playingLimit = 5 ;

    public RegularBehavior() {
        this.playingLimit = 5;
    }

    public int getRemainingPlays() {
        return playingLimit;
    }

    @Override
    public void createPlaylist(String Title, User Owner){
        throw new InvalidOperationException("Playlist creation is a premium feature. Upgrade to unlock this and more!");
    }

    @Override
    public void playMusic(Music music){
        if( playingLimit > 0 ){
            music.play();
            playingLimit--;
        }
        else
            throw new InvalidOperationException("You've reached the limit for free listening. Upgrade to Premium for unlimited music!");

    }

    @Override
    public void buyPremium(User owner, int month) {
        if (owner == null) {
            throw new IllegalArgumentException("Owner cannot be null");
        }

        if (month <= 0) {
            throw new IllegalArgumentException("Month must be positive");
        }

        owner.setBehavior(new PremiumBehavior(month));
    }
}
