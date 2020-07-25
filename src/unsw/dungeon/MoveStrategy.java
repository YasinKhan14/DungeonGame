package unsw.dungeon;


public interface MoveStrategy {
    
    public void nextMove(Player player, Player player2, Enemy enemy);

}