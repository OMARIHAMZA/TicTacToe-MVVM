package omari.hamza.tictactoe.viewmodel;

import androidx.databinding.ObservableArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import omari.hamza.tictactoe.model.Cell;
import omari.hamza.tictactoe.model.Game;
import omari.hamza.tictactoe.model.Player;

public class GameViewModel extends ViewModel {

    public ObservableArrayMap<String, String> cells;
    private Game game;


    public void init(String player1, String player2) {
        cells = new ObservableArrayMap<>();
        game = new Game(player1, player2);
    }

    public void onClickedCellAt(int row, int column) {
        if (game.getCells() == null) return;
        if (game.getCells()[row][column] == null) {
            game.getCells()[row][column] = new Cell(game.getCurrentPlayer());
            cells.put(stringFromNumbers(row, column), game.getCurrentPlayer().getValue());
            if (game.hasGameEnded())
                game.reset();
            else
                game.switchPlayer();
        }
    }

    public LiveData<Player> getWinner() {
        return game.winner;
    }

    private static String stringFromNumbers(int... numbers) {
        StringBuilder sNumbers = new StringBuilder();
        for (int number : numbers)
            sNumbers.append(number);
        return sNumbers.toString();
    }


}
