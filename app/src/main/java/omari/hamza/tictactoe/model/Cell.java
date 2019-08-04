package omari.hamza.tictactoe.model;

public class Cell {

    private Player player;

    public Cell(Player player) {
        this.player = player;
    }

    public boolean isEmpty(){
        return player == null || player.getValue().isEmpty() || player.getValue() == null;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
