package unsw.dungeon;

public class Weapon extends Entity {
    
    private boolean pickedUp;
    private int charges;
    public Weapon(int x, int y) {
        super(x, y);
        this.charges = 5;
        this.pickedUp = false;
    }
    @Override
    public boolean allowPass(Moveable moveable) {
        if (moveable instanceof Player){
            Player player = (Player) moveable;
            player.equipSword(this);
            player.playerRemove(this);
            pickedUp = true;
        }
        return true;
    }

    @Override
    public boolean isDestroyed() {
        return pickedUp;
    }

    public int getCharges() {
        return charges;
    }

    public void setCharges(int charges) {
        this.charges = charges;
    }
    
    
    
}