/*package unsw.dungeon;
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

    @Override
    public void nextMove(Player player, Player player2, Enemy enemy) {

        List<Node> nodeList = new ArrayList<Node>();
        List<Node> openList = new ArrayList<Node>();
        List<Node> closedList = new ArrayList<Node>();
        boolean isReachable;

        for (int i = 0; i < dungeon.getHeight(); i++){
            for (int j = 0; j < dungeon.getWidth(); j++) {
                isReachable = true;
                List<Entity> entityList = map[i][j];
                for (Entity entity : entityList) {
                    if (entity instanceof Wall || entity instanceof Boulder || entity instanceof Door) {
                        isReachable = false;
                        break;
                    }
                }
                if (isReachable)
                    nodeList.add(new Node(j, i));
            }
        }

        for (Node node : nodeList) {
            node.setH(euclideanDistance(player.getX(), player.getY(), node.getX(), node.getY()));
        }

        Node startPoint = new Node(enemy.getX(), enemy.getY());
        Node endPoint = new Node(player.getX(), player.getY());

        startPoint.setG(0);
        openList.add(startPoint);

        for (int i = 0; i < openList.size(); i++) {

            Node curr = openList.get(0);
            /*if (closedList.contains(curr)) {
                openList.remove(curr);
                continue;
            }*/
            /*
            if (curr.getX() == endPoint.getX() && curr.getY() == endPoint.getY()) {
                //System.out.println("reached dest");
                Node initialMove = null;
                if (isNeighbour(startPoint, endPoint)) {
                    initialMove = endPoint;
                    System.out.println("direct next to found");
                }
                else {
                    for (int k = 0; k < closedList.size(); k++) {
                        if (isNeighbour(closedList.get(k), startPoint)) {
                            System.out.println("neighbour found");
                            initialMove = closedList.get(k);
                        }
                    }
                    if (closedList.size() == 0) {
                        System.out.println("no neighbours in closed list");
                    }
                }
                
                if (initialMove == null) {
                    System.out.println("null failure");
                    return;
                }
                if (initialMove.getX() == startPoint.getX()) {
                    if (initialMove.getY() == startPoint.getY() + 1) {
                        enemy.moveDown();
                        System.out.println("path found");
                    }
                        
                    else if (initialMove.getY() == startPoint.getY() - 1) {
                        enemy.moveUp();
                        System.out.println("path found");
                    }
                }
                else if (initialMove.getY() == startPoint.getY()) {
                    if (initialMove.getX() == startPoint.getX() + 1) {
                        enemy.moveRight();
                        System.out.println("path found");
                    }
                    else if (initialMove.getX() == startPoint.getX() - 1 ){
                        enemy.moveLeft();
                        System.out.println("path found");
                    }  
                }
                System.out.println("failed from not finding intial move");
                return;
            }
                

            List<Node> neighboursList = getNeighbours(curr, nodeList);
            for (Node node : neighboursList) { //add all unvisited neighbours
                if (!closedList.contains(node)) {
                    node.setF(1 + curr.getG());
                    openList.add(node);
                }
                    
            }
            closedList.add(curr); //pop current node off queue
            openList.remove(curr);
            openList.sort(Comparator.comparing(Node::getF));
            
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
    
} */