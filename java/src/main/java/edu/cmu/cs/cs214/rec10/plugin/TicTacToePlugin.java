package edu.cmu.cs.cs214.rec10.plugin;

import edu.cmu.cs.cs214.rec10.framework.core.GameFramework;
import edu.cmu.cs.cs214.rec10.framework.core.GamePlugin;
import edu.cmu.cs.cs214.rec10.games.TicTacToe;

/**
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class TicTacToePlugin implements GamePlugin<String> {
    private static final String GAME_NAME = "Tic Tac Toe";
    private static final String PLAY_MESSAGE = "Select a cell";
    private static final String WIN_MESSAGE = "Win: %s";

    private TicTacToe game;
    private GameFramework framework;

    @Override
    public String getGameName() {
        return GAME_NAME;
    }

    @Override
    public int getGridWidth() {
        return game.SIZE;
    }

    @Override
    public int getGridHeight() {
        return game.SIZE;
    }

    @Override
    public void onRegister(GameFramework framework) {
        this.framework = framework;
    }

    @Override
    public void onNewGame() {
        game = new TicTacToe();
        framework.setFooterText(PLAY_MESSAGE);
    }

    @Override
    public void onNewMove() {

    }

    @Override
    public boolean isMoveValid(int x, int y) {
        return game.isValidPlay(x, y);
    }

    @Override
    public boolean isMoveOver() {
        return true;
    }

    @Override
    public void onMovePlayed(int x, int y) {
        game.play(x, y);
        framework.setSquare(x, y, game.getSquare(x, y).name());
        if (isGameOver()) {
            framework.setFooterText("Game Over");
        }
    }

    @Override
    public boolean isGameOver() {
        return game.isOver();
    }

    @Override
    public String getGameOverMessage() {
        return String.format(WIN_MESSAGE, game.winner().name());
    }

    @Override
    public void onGameClosed() {

    }

    @Override
    public String currentPlayer() {
        return game.currentPlayer().name();
    }
}
