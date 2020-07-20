package unsw.dungeon;
import java.lang.Math;


public class EscapeStrategy implements MoveStrategy {

    public void nextMove(Player player, Enemy enemy) {
         
        double leftCost = -1;
        double rightCost = -1;
        double upCost = -1;
        double downCost = -1;

        if (enemy.canMove(enemy.getX(), enemy.getY() - 1))
            upCost = euclideanDistance(enemy.getX(), enemy.getY() - 1, player.getX(), player.getY());

        if(enemy.canMove(enemy.getX(), enemy.getY() + 1))
            downCost = euclideanDistance(enemy.getX(), enemy.getY() + 1, player.getX(), player.getY());

        if(enemy.canMove(enemy.getX() -1, enemy.getY()))
            leftCost = euclideanDistance(enemy.getX() - 1, enemy.getY(), player.getX(), player.getY());

        if (enemy.canMove(enemy.getX() + 1, enemy.getY()))
            rightCost = euclideanDistance(enemy.getX() + 1, enemy.getY(), player.getX(), player.getY());

        if (isMax(rightCost, leftCost, downCost, upCost))
            enemy.moveRight();
        else if (isMax(leftCost, rightCost, downCost, upCost))
            enemy.moveLeft();
        else if (isMax(upCost, leftCost, downCost, rightCost))
            enemy.moveUp();
        else 
            enemy.moveDown();
    }

    private boolean isMax(double comp, double x1, double x2, double x3) {
		double costArray[] = {comp, x1, x2, x3};
		if (comp == -1)
            return false;
		for (double d : costArray) {
            if (d == -1)
                continue;
			if (comp < d)
				return false;
		}
		return true;
	}

	private double euclideanDistance (int x1, int y1, int x2, int y2) {
		double euclideanDistance = Math.sqrt((x2 - x1)*(x2 - x1) + (y2 - y1) * (y2 - y1));
		return euclideanDistance;
		
	}
            
}