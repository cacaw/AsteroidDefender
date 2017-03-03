package Controller;

import java.io.IOException;

/**
 * Created by Patricki on 3/3/2017.
 */
public class GameController {
    
    static Game AsteroidDefender;
    
    public static void NewGame() throws IOException {
        AsteroidDefender = new Game();
        AsteroidDefender.getUi().setGame(AsteroidDefender);
        AsteroidDefender.GameStart();
    }
    
}
