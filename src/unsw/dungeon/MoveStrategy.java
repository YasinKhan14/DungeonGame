package unsw.dungeon;


public interface MoveStrategy {
    
    public void nextMove(Player player, Enemy enemy);

}