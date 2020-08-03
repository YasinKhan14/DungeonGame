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
        startPoint.setF(0);
        openList.add(startPoint);

        while (!openList.isEmpty()) {
            openList.sort(Comparator.comparing(Node::getF));

            Node curr = openList.remove(0);

            List<Node> neighboursList = getNeighbours(curr, nodeList);

            for (Node node : neighboursList) { 
                if (node.getX() == endPoint.getX() && node.getY() == endPoint.getY()) {
                    
                    for (Node node2 : closedList) {
                        System.out.println("x:" + node2.getX() + " y:" + node2.getY());
                    }
                    return;
                }
                node.setG(curr.getG() + 1);
                node.setF(node.getG() + node.getH());

                if (containsLower(openList, node))
                    continue;
                if (containsLower(closedList, node)) {
                    continue;
                }
                else {
                    int x = node.getX();
                    int y = node.getY();
                    int g = node.getG();
                    double h = node.getH();
                    double f = node.getF();
                    
                    Node cloneNode = new Node(x, y);
                    node.setG(g);
                    node.setH(h);
                    node.setF(f);
                    openList.add(cloneNode);
                }
                
                    
            }
            int x = curr.getX();
            int y = curr.getY();
            int g = curr.getG();
            double h = curr.getH();
            double f = curr.getF();
            
            Node cloneNode = new Node(x, y);
            curr.setG(g);
            curr.setH(h);
            curr.setF(f);
            closedList.add(cloneNode);
            
            
        }
        

    }


	private double euclideanDistance (int x1, int y1, int x2, int y2) {
        double euclideanDistance = Math.abs(x2 - x1) + Math.abs(y2 - y1);
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
    

    public boolean containsLower(List<Node> nodeL, Node node) {
        for (Node nodeC : nodeL) {
            if (nodeC.getX() == node.getX() && nodeC.getY() == node.getY()) {
                if (nodeC.getF() < node.getF()) 
                    return true;
            }        
        }
        return false;
    }

}*/