import textgame.player.PlayerClient;

/**
 * Kør denne for at spille med. Start den én gang for hver spiller.
 *
 * Det her er ikke en del af dit spil — den starter bare spiller-programmet,
 * så du har en grøn pil at trykke på. Serveren skal stå det samme sted som i
 * MyGame.
 */
public class Spiller {

    public static void main(String[] args) {
        PlayerClient.main(new String[] {"game.tobiasgrundtvig.dk", "4000"});
    }
}
