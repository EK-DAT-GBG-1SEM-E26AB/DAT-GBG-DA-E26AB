import textgame.player.PlayerClient;

/**
 * Run this to play. Start it once for every player.
 *
 * This is not part of your game — it only starts the player program, so that
 * you have a green arrow to press. The server has to be the same one as in
 * MyGame.
 */
public class StartPlayer {

    public static void main(String[] args) {
        PlayerClient.main(new String[] {"game.tobiasgrundtvig.dk", "4000"});
    }
}
