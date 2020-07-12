package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class BasicGoal implements Goal {

    private String name;
    private List<Interactable> goalEntityList = new ArrayList<>();

    public BasicGoal(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        for (Interactable interactable : goalEntityList) {
            if (!interactable.defeatedObject())
                return false;
        }
        return true;
    }

    public void attachGoalEntity(Interactable goalEntity) {
        //use this function attach all tresures/keys or door object
        goalEntityList.add(goalEntity);
    }
    
    
    
}