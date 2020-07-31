package unsw.dungeon;

public class Exit extends Entity{
    
    private Goal goal;

    public Exit(int x, int y, Goal goal) {
        super(x, y);
        this.goal = goal;
    }
    
    @Override
    public boolean allowPass(Moveable moveable) {
        if (moveable instanceof Player) {
            //((Player)moveable).defeated();
            return true;
        }
        return false;
    }

    @Override
    public boolean isDestroyed() {
        return checkExitConjunction(goal);
    }

    public boolean checkExitConjunction(Goal goal) {
        if (goal instanceof ComplexGoal) {
            Goal leftGoal = ((ComplexGoal) goal).getSubgoalPair(0);
            Goal rightGoal = ((ComplexGoal) goal).getSubgoalPair(1);

            if (rightGoal instanceof BasicGoal) {     //right child is a leaf
                BasicGoal basicGoal = (BasicGoal) rightGoal;
                if (basicGoal.containsGoal(this)) {
                    return rightGoal.isCompleted();
                }
                return checkExitConjunction(rightGoal); //recurse left subtree 
            }
            else if (leftGoal instanceof BasicGoal) { //left child is a leaf
                BasicGoal basicGoal = (BasicGoal) leftGoal;
                if (basicGoal.containsGoal(this)) {
                    return leftGoal.isCompleted();
                }
                return checkExitConjunction(rightGoal); //recurse right subtree
            }
            return checkExitConjunction(leftGoal) && checkExitConjunction(rightGoal); //recurse for both left and right subtree
        }
        return true; //case for if goal is just exit alone.
        
    }
    
        
    
}