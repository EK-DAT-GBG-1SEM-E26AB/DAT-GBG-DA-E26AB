import textgame.Match;
import textgame.Player;
import textgame.Room;

/**
 * One game, played by one group. This is where you write the game itself.
 *
 * Write play() as an ordinary program: loops, ifs and local variables.
 * p.askInt(...) waits for an answer exactly like a Scanner does — the keyboard
 * is just on somebody else's computer.
 *
 * When play() is finished, the game is over.
 */
public class MyGameMatch implements Match {

    public void play(Room room) {
        room.tellAll("Velkommen! Højeste tal vinder.");

        Player winner = null;
        int best = -1;

        for (Player p : room.players()) {
            int number = p.askInt("Vælg et tal fra 1 til 100", 1, 100);

            room.tellAll(p.name() + " valgte " + number + ".");

            if (number > best) {
                best = number;
                winner = p;
            }
        }

        room.tellAll(winner.name() + " vandt med " + best + "!");

        // From here on: write your own game.
    }
}
