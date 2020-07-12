package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class ComplexGoal implements Goal {

    private String name;
    private String operator;
    private List<Goal> subgoalPair = new ArrayList<>();

    public ComplexGoal(String name, String operator) {
        this.name = name;
        this.operator = operator;
    }

    public void  attachGoal(Goal goal) {
        subgoalPair.add(goal);
    }

    public boolean isCompleted() {
        if (operator.equals("and")) {
            return subgoalPair.get(0).isCompleted() && subgoalPair.get(1).isCompleted();
        }
        else {
            return subgoalPair.get(0).isCompleted() || subgoalPair.get(1).isCompleted();
        }
    }
    
}