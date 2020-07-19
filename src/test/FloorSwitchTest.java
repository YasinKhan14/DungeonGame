public class FloorSwitch {

    @Test
    public void nothingOnSwitch(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        Boulder boulder = new Boulder(1,0, dungeon);
        dungeon.setPlayer(player);
        dungeon.addEntity(boulder);
        player.moveRight();
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);
        assertEquals(boulder.getX(), 1);
        assertEquals(boulder.getY(), 0);
    }

    @Test
    public void playerOnSwitch(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        Boulder boulder = new Boulder(1,0, dungeon);
        dungeon.setPlayer(player);
        dungeon.addEntity(boulder);
        player.moveRight();
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);
        assertEquals(boulder.getX(), 1);
        assertEquals(boulder.getY(), 0);
    }
    
    @Test
    public void boulderOnSwitch(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        Boulder boulder = new Boulder(1,0, dungeon);
        dungeon.setPlayer(player);
        dungeon.addEntity(boulder);
        player.moveRight();
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);
        assertEquals(boulder.getX(), 1);
        assertEquals(boulder.getY(), 0);
    }

}