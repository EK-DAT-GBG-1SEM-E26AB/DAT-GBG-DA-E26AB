import textgame.Match;
import textgame.Player;
import textgame.Room;

/**
 * Ét spil, spillet af én gruppe. Her skriver du selve spillet.
 *
 * Skriv play() som et helt almindeligt program: løkker, if'er og lokale variable.
 * p.askInt(...) venter på svar præcis som en Scanner - tastaturet står bare på
 * en anden computer.
 *
 * Når play() er færdig, er spillet slut.
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

        // Herfra og ned: skriv dit eget spil.
    }
}
