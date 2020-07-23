package unsw.dungeon;

public class Exit extends Entity{

    public Exit(int x, int y) {
        super(x, y);
    }
    
    public boolean allowPass(Moveable moveable) {
        if (moveable instanceof Player) {
            ((Player)moveable).defeated();
            return true;
        }
        return false;
    }

    public boolean isDestroyed() {
        return false;
    }
    
}