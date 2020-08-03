package unsw.dungeon;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.lang.Math;


public class Astar implements MoveStrategy {

    private List<Entity>[][] map;
    private Dungeon dungeon;

    public Astar(Dungeon dungeon) {
        this.map = dungeon.getMap();
        this.dungeon = dungeon;

        
    }
    //(map[i][j]).size() == 0

    @Override
    public void nextMove(Player player, Player player2, Enemy enemy) {

        List<Node> nodeList = new ArrayList<Node>();
        List<Node> openList = new ArrayList<Node>();
        List<Node> closedList = new ArrayList<Node>();

        for (int i = 0; i < dungeon.getHeight(); i++){
            for (int j = 0; j < dungeon.getWidth(); j++) {
                List<Entity> entityList = map[i][j];
                for (Entity entity : entityList) {
                    if (!(entity instanceof Wall || entity instanceof Boulder || entity instanceof Door)) {
                        nodeList.add(new Node(j, i));
                    }
                }
            }
        }

        for (Node node : nodeList) {
            node.setH(euclideanDistance(player.getX(), player.getY(), enemy.getX(), enemy.getY()));
        }

        Node startPoint = new Node(enemy.getX(), enemy.getY());
        //Node destinationPoint = new Node(player.getX(), player.getY());

        openList.add(startPoint);

        for (int i = 0; i < openList.size(); i++) {
            
            Node curr = openList.get(i);

            if (curr.getX() == player.getX() && curr.getY() == player.getY()) {
                Node firstMove = null;
                for (int k = 0; k < closedList.size(); k++) {
                    if (isNeighbour(closedList.get(k), startPoint)) {
                        firstMove = closedList.get(k);
                    }
                }
                if (firstMove != null) {
                    if (firstMove.getX() == startPoint.getX()) {
                        if (firstMove.getY() == startPoint.getY() + 1)  {
                            enemy.moveDown();
                            return;
                        }
                        else {
                            enemy.moveUp();
                            return;
                        }
                    }
                    else {
                        if (firstMove.getX() == startPoint.getX() + 1) {
                            enemy.moveRight();
                        }
                        else {
                            enemy.moveLeft();
                        }
                    }
                }
                
            }
                

            List<Node> neighboursList = getNeighbours(openList.get(i), nodeList);
            for (Node node : neighboursList) { //add all unvisited neighbours
                if (!closedList.contains(node));
                    openList.add(node);
            }
            closedList.add(openList.remove(i)); //pop current node off queue
            openList.sort(Comparator.comparing(Node::getH));
            
        }
        

    }


	private double euclideanDistance (int x1, int y1, int x2, int y2) {
		double euclideanDistance = Math.sqrt((x2 - x1)*(x2 - x1) + (y2 - y1) * (y2 - y1));
		return euclideanDistance;
		
    }
    
    private List<Node> getNeighbours(Node node, List<Node> nodeList) {

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