package unsw.dungeon;

public class Exit extends Entity{
    
    private Goal goal;
    private Player player;
    private Boolean memory;

    public Exit(int x, int y) {
        super(x, y);
        this.memory = false;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    
    @Override
    public boolean allowPass(Moveable moveable) {
        if (moveable instanceof Player) {
            //((Player)moveable).defeated();
            memory = checkExitConjunction(goal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isDestroyed() {
        //if (player.getX() != this.getX() || player.getY() != this.getY())
        //    return false;
        return memory;//checkExitConjunction(goal);
    }

    public boolean checkExitConjunction(Goal goal) {

        if (goal instanceof ComplexGoal) {
            ComplexGoal complexGoal = ((ComplexGoal) goal);
            Goal leftGoal = complexGoal.getSubgoalPair(0);
            Goal rightGoal = complexGoal.getSubgoalPair(1);

            if (rightGoal instanceof BasicGoal) {     //right child is a leaf
                BasicGoal basicGoal = (BasicGoal) rightGoal;
                if (basicGoal.containsGoal(this)) {
                    return leftGoal.isCompleted();
                }
                return checkExitConjunction(leftGoal); //recurse left subtree 
            }
            else if (leftGoal instanceof BasicGoal) { //left child is a leaf
                BasicGoal basicGoal = (BasicGoal) leftGoal;
                if (basicGoal.containsGoal(this)) {
                    return rightGoal.isCompleted();
                }
                return checkExitConjunction(rightGoal); //recurse right subtree
            }
            if (complexGoal.getName().equals("and")) {
                return checkExitConjunction(leftGoal) && checkExitConjunction(rightGoal); //check for both left AND right subtree
            }
            else {
                return checkExitConjunction(leftGoal) || checkExitConjunction(rightGoal); //recurse for left OR right subtree
            }
            
        }
        return ((BasicGoal) goal).containsGoal(this); //case for if goal is just exit alone.
        
    }
    
        
    
}