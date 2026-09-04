import textgame.Game;
import textgame.GameServer;
import textgame.Match;

/**
 * Beskrivelsen af dit spil: hvad det hedder, og hvor mange der kan spille.
 *
 * Omdøb klassen til noget, der passer til dit spil (højreklik -> Refactor -> Rename),
 * og husk at omdøbe MyGameMatch på samme måde.
 */
public class MyGame implements Game {

    public String name() {
        return "Mit spil";                       // vises i lobbyen
    }

    public String description() {
        return "En linje, der fortæller hvad spillet går ud på.";
    }

    public int minPlayers() {
        return 2;                                // færrest spillere
    }

    public int maxPlayers() {
        return 4;                                // flest spillere
    }

    public Match newMatch() {
        return new MyGameMatch();                // ALTID et nyt objekt
    }

    public static void main(String[] args) {
        // Klasseserveren. Kører hele tiden — du skal ikke selv starte noget.
        // Spiller I et andet sted, skal adressen skiftes her OG i Spiller.
        GameServer.connect("game.tobiasgrundtvig.dk", 4000)
                  .host(new MyGame());
    }
}
