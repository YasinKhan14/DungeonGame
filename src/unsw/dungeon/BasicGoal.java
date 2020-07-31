package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class BasicGoal implements Goal {

    private String name;
    private List<Entity> goalEntityList = new ArrayList<>();

    public BasicGoal(String name) {
        this.name = name;
    }

    @Override
    public String getName(){
        return name;
    }
    @Override
    public boolean isCompleted() {
        for (Entity entity : goalEntityList) {
            if (!entity.isDestroyed())
                return false;
        }
        return true;
    }

    public void attachGoalEntity(Entity goalEntity) {
        //use this function attach all tresures/keys or door object
        goalEntityList.add(goalEntity);
    }
    
    public void setGoalEntity(ArrayList<? extends Entity> goalEntityList){
        this.goalEntityList.addAll(goalEntityList);
    }

    public boolean containsGoal(Entity entity) {
        return goalEntityList.contains(entity);
    }
    
    
}