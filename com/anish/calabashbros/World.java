package com.anish.calabashbros;

//观察发现，world本身已经是一个方阵了，写作业的时候要考虑考虑如何运用这个性质
public class World {
    //它奶奶个腿，这个WIDTH,Height上去就被使用了，我们改是后面的，也就是说一个world多大创建前就已经确定了
    //行了，解决一个小问题
    public static int WIDTH = 60;
    public static int HEIGHT = 60;

    private Tile<Thing>[][] tiles;

    public World(int row, int column) {
        WIDTH = row;
        HEIGHT = column;
        if (tiles == null) {
            tiles = new Tile[WIDTH][HEIGHT];
        }

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                tiles[i][j] = new Tile<>(i, j);
                tiles[i][j].setThing(new Floor(this));
            }
        }
    }
    public World()
    {
        this(WIDTH, HEIGHT);
    }

    public Thing get(int x, int y) {
        return this.tiles[x][y].getThing();
    }

    public void put(Thing t, int x, int y) {
        this.tiles[x][y].setThing(t);
    }

}
