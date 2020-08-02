package unsw.dungeon;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class Astar implements MoveStrategy {

    private List<Node> nodeList;
    private List<Node> closedList;
    private List<Node> openList;
    private List<Entity>[][] map;
    private Dungeon dungeon;

    public Astar(Dungeon dungeon) {
        this.map = dungeon.getMap();
        this.dungeon = dungeon;
        this.nodeList = new ArrayList<Node>();
        this.openList = new ArrayList<Node>();
        this.closedList = new ArrayList<Node>();

        for (int i = 0; i < dungeon.getHeight(); i++){
            for (int j = 0; j < dungeon.getWidth(); j++) {
                if ((map[i][j]).size() == 0) {
                    nodeList.add(new Node(j, i));
                }
            }
        }
    }

    @Override
    public void nextMove(Player player, Player player2, Enemy enemy) {

        for (Node node : nodeList) {
            node.setH(euclideanDistance(player.getX(), player.getY(), enemy.getX(), enemy.getY()));
        }

        Node startPoint = new Node(enemy.getX(), enemy.getY());
        Node destinationPoint = new Node(player.getX(), player.getY());

        openList.add(currentPoint);

        for (Node node : openList) {
                
        }
        

    }

    private boolean isMin(double comp, double x1, double x2, double x3) {
        double costArray[] = {comp, x1, x2, x3};
        if (comp == -1)
            return false;
		for (double d : costArray) {
            if (d == -1)
                continue;
			if (comp > d)
				return false;
		}
		return true;
	}

	private double euclideanDistance (int x1, int y1, int x2, int y2) {
		double euclideanDistance = Math.sqrt((x2 - x1)*(x2 - x1) + (y2 - y1) * (y2 - y1));
		return euclideanDistance;
		
    }
    
    private List<Node> getNeighbours(Node node) {

        List<Node> neighbourList = new ArrayList<Node>();

        for (Node nodeC : nodeList) {
            if (isNeighbour(node, nodeC)) 
                neighbourList.add(nodeC);
        }
        return neighbourList;
    }

    private boolean isNeighbour(Node node1, Node node2) {
        if (node2.getX() == node1.getX()) {
            if (node2.getY() == node1.getY() + 1 || node2.getY() == node1.getY() - 1) {
                return true;
            }
        }
        else if (node2.getY() == node1.getY()) {
            if (node2.getX() == node1.getX() + 1 || node2.getX() == node1.getX() - 1) {
                return true;
            }
        }
        return false;
    }
    
}