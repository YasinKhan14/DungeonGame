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

        if (player2 != null) {
            if (player.isDestroyed()) {
                player = player2;
            }
            else if (!(player2.isDestroyed()) && (manHattenDistance(enemy.getX(), enemy.getY(), player2.getX(), player2.getY()) < manHattenDistance(enemy.getX(), enemy.getY(), player.getX(), player.getY()))) {
                player = player2;
            }      
        }

        List<Node> nodeList = new ArrayList<Node>();
        List<Node> priorityQueue = new ArrayList<Node>();
        boolean isReachable;

        for (int i = 0; i < dungeon.getHeight(); i++){
            for (int j = 0; j < dungeon.getWidth(); j++) {
                isReachable = true;
                List<Entity> entityList = map[i][j];
                for (Entity entity : entityList) {
                    if (entity instanceof Wall || entity instanceof Boulder || entity instanceof Door) {
                        if (entity instanceof Door && ((Door)entity).isDestroyed()){
                            continue;
                        }
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
        priorityQueue.add(startPoint);

        while (!priorityQueue.isEmpty()) {
            priorityQueue.sort(Comparator.comparing(Node::getF));

            Node curr = priorityQueue.remove(0);

            if (curr.getX() == endPoint.getX() && curr.getY() == endPoint.getY()) {
                if (curr.getPrev() == null)
                    return;
                while (curr.getPrev().getPrev() != null) {
                    curr = curr.getPrev();
                }
                if (curr.getX() == startPoint.getX()) {
                    if (curr.getY() == startPoint.getY() + 1)
                        enemy.moveDown();
                    else
                        enemy.moveUp();
                }
                else {
                    if (curr.getX() == startPoint.getX() + 1)
                        enemy.moveRight();
                    else
                        enemy.moveLeft();
                }
                return;
            }

            List<Node> neighboursList = getNeighbours(curr, nodeList);

            for (Node node : neighboursList) { 
                int g = curr.getG() + 1;
                if (node.getG() == -1 || g < node.getG()) {
                    node.setG(g);
                    node.setF(g + node.getH());
                    priorityQueue.add(node);
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