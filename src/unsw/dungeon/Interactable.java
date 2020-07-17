package unsw.dungeon;

public interface Interactable {
    
    public int moveableIntersect(Moveable moveable);
    public boolean defeatedObject();

}