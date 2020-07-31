package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class ComplexGoal implements Goal {

    private String operator;
    private List<Goal> subgoalPair = new ArrayList<>();

    public ComplexGoal(String operator) {
        this.operator = operator;
    }

    public void  attachGoal(Goal goal) {
        subgoalPair.add(goal);
    }
    public List<Goal> getGoals(){
        return subgoalPair;
    }
    @Override
    public String getName(){
        return operator;
    }
    @Override
    public boolean isCompleted() {
        if (operator.equals("and")) {
            return subgoalPair.get(0).isCompleted() && subgoalPair.get(1).isCompleted();
        }
        else {
            return subgoalPair.get(0).isCompleted() || subgoalPair.get(1).isCompleted();
        }
    }


    public Goal getSubgoalPair(int index) {
        return subgoalPair.get(index);
    }
    
}