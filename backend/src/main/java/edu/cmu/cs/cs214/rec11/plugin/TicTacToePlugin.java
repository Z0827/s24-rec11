package edu.cmu.cs.cs214.rec11.plugin;

import edu.cmu.cs.cs214.rec11.framework.core.GameFramework;
import edu.cmu.cs.cs214.rec11.framework.core.GamePlugin;
import edu.cmu.cs.cs214.rec11.games.TicTacToe;
import edu.cmu.cs.cs214.rec11.games.TicTacToe.Player;

public class TicTacToePlugin implements GamePlugin<String>{

    private static final String GAME_NAME = "TicTacToe!";

    private static final int WIDTH = TicTacToe.SIZE;
    private static final int HEIGHT = TicTacToe.SIZE;

    private static final String PLAYER_WON_MSG = "won!";
    private static final String GAME_TIED_MSG = "The game ended in a tie.";

    private static final String GAME_START_FOOTER = "You are playing TicTacToe";

    private GameFramework framework;
    private TicTacToe gameInstance;

    public String getGameName(){
        return GAME_NAME;
    }

    public int getGridWidth(){
        return WIDTH;
    }

    public int getGridHeight(){
        return HEIGHT;
    }

    public void onRegister(GameFramework framework){
        this.framework = framework;
    }

    public void onNewGame(){
        this.gameInstance = new TicTacToe();
        this.framework.setFooterText(GAME_START_FOOTER);
    }

    public void onNewMove(){
        // seems nothing have to be done?
    }

    public boolean isMoveValid(int x, int y){
        // as long as that position is not occupied
        return gameInstance.isValidPlay(x, y);
    }

    public boolean isMoveOver(){
        return true;
    }

    public void onMovePlayed(int x, int y){
        gameInstance.play(x, y);
        this.framework.setSquare(x, y, gameInstance.currentPlayer().toString());
    }

    public boolean isGameOver(){
        return gameInstance.isOver();
    }

    public String getGameOverMessage(){
        Player result = gameInstance.winner();
        if(result == null){
            return GAME_TIED_MSG;
        } else {
            return result.ordinal() + PLAYER_WON_MSG;
        }
    }
    
    public void onGameClosed(){
        // do nothing ?
    }

    public String currentPlayer(){
        return gameInstance.currentPlayer().ordinal() + "";

    }
}
