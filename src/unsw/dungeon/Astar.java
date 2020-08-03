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
            node.setH(manHattenDistance(player.getX(), player.getY(), node.getX(), node.getY()));
        }

        Node startPoint = new Node(enemy.getX(), enemy.getY());
        Node endPoint = new Node(player.getX(), player.getY());

        startPoint.setG(0);
        startPoint.setF(0);
        startPoint.setPrev(null);
        openList.add(startPoint);

        while (!openList.isEmpty()) {
            openList.sort(Comparator.comparing(Node::getF));

            Node curr = openList.remove(0);
            if (curr.getPrev() != null) {
                //System.out.println("x:" + curr.getX() + " y:" + curr.getY() + "prev(" + curr.getPrev().getX() + " ," + curr.getPrev().getY() + ")");
            }

            if (curr.getX() == endPoint.getX() && curr.getY() == endPoint.getY()) {
                Node traceNode = curr;
                System.out.println("found path:");
                int kn = 0;
                while (traceNode != null && kn < 5) {
                    System.out.println("x:" + traceNode.getX() + " y:" + traceNode.getY());
                    Node temp = traceNode.getPrev();
                    traceNode = temp;
                    kn++;
                }
                System.out.println("done!");
                return;
            }

            List<Node> neighboursList = getNeighbours(curr, nodeList);

            for (Node node : neighboursList) { 
                int g = curr.getG() + 1;
                if (!openList.contains(node) || g < node.getG()) {
                    node.setG(g);
                    node.setF(g + node.getH());
                    openList.add(node);
                    node.setPrev(curr);
                } 
            } 
        }
    }


	private int manHattenDistance (int x1, int y1, int x2, int y2) {
        int manHatten = Math.abs(x2 - x1) + Math.abs(y2 - y1);
		return manHatten;
		
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

}