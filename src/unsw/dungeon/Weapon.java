package unsw.dungeon;

public class Weapon extends Entity {
    
    private int charges;
    public Weapon(int x, int y) {
        super(x, y);
        this.charges = 5;
    }
    @Override
    public boolean allowPass(Moveable moveable) {
        if (moveable instanceof Player){
            Player player = (Player) moveable;
            player.equipSword(this);
            player.playerRemove(this);
            setOffMap();
        }
        return true;
    }

    public int getCharges() {
        return charges;
    }

    public void setCharges(int charges) {
        this.charges = charges;
    }
    
    public void swing(){
        charges -= 1;
    }
    
    
}