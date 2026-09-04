import textgame.Game;
import textgame.GameServer;
import textgame.Match;

/**
 * Describes the game: what it is called, and how many can play.
 *
 * Rename this class to something that fits your game (right-click -> Refactor ->
 * Rename), and rename MyGameMatch the same way.
 *
 * The text inside the quotes is what players see, so write that in Danish.
 * Everything else — class names, variables, comments — is English.
 */
public class MyGame implements Game {

    public String name() {
        return "Mit spil";                       // shown in the lobby
    }

    public String description() {
        return "En linje, der fortæller hvad spillet går ud på.";
    }

    public int minPlayers() {
        return 2;                                // fewest players
    }

    public int maxPlayers() {
        return 4;                                // most players
    }

    public Match newMatch() {
        return new MyGameMatch();                // ALWAYS a new object
    }

    public static void main(String[] args) {
        // The class server. It runs all the time, so you do not start anything.
        // Playing somewhere else? Change the address here AND in StartPlayer.
        GameServer.connect("game.tobiasgrundtvig.dk", 4000)
                  .host(new MyGame());
    }
}
