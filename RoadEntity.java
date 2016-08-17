import javafx.scene.image.Image;

public class RoadEntity extends Entity {
    private static Image defaultImage = new Image("assets/images/Tile/medievalTile_57.png");

    private static Image roadImages[] = new Image[]{
        new Image("assets/images/Tile/medievalTile_03.png"),
        new Image("assets/images/Tile/medievalTile_04.png"),
        new Image("assets/images/Tile/medievalTile_05.png"),
        new Image("assets/images/Tile/medievalTile_06.png"),
        new Image("assets/images/Tile/medievalTile_07.png"),
        new Image("assets/images/Tile/medievalTile_20.png"),
        new Image("assets/images/Tile/medievalTile_21.png"),
        new Image("assets/images/Tile/medievalTile_17.png"),
        new Image("assets/images/Tile/medievalTile_18.png"),
        new Image("assets/images/Tile/medievalTile_19.png"),
        new Image("assets/images/Tile/medievalTile_31.png"),
        new Image("assets/images/Tile/medievalTile_32.png"),
        new Image("assets/images/Tile/medievalTile_33.png"),
        new Image("assets/images/Tile/medievalTile_34.png"),
        new Image("assets/images/Tile/medievalTile_35.png"),
    };

    // Format:
    // 0 - Does not matter
    // 1 - Can not be present
    // 2 - Must be present

    private static int roadConfigurations[][][] = new int[][][]{
        // Vertial One Way
        new int[][]{
            new int[]{ 0, 2, 0 },
            new int[]{ 1, 2, 1 },
            new int[]{ 0, 2, 0 },
        },

        // Horizontal One Way
        new int[][]{
            new int[]{ 0, 1, 0 },
            new int[]{ 2, 2, 2 },
            new int[]{ 0, 1, 0 },
        },

        // All Way
        new int[][]{
            new int[]{ 0, 2, 0 },
            new int[]{ 2, 2, 2 },
            new int[]{ 0, 2, 0 },
        },

        // Three way down
        new int[][]{
            new int[]{ 0, 1, 0 },
            new int[]{ 2, 2, 2 },
            new int[]{ 0, 2, 0 },
        },

        // Three way up
        new int[][]{
            new int[]{ 0, 2, 0 },
            new int[]{ 2, 2, 2 },
            new int[]{ 0, 1, 0 },
        },

        // Three way left
        new int[][]{
            new int[]{ 0, 2, 0 },
            new int[]{ 2, 2, 1 },
            new int[]{ 0, 2, 0 },
        },

        // Three way right
        new int[][]{
            new int[]{ 0, 2, 0 },
            new int[]{ 1, 2, 2 },
            new int[]{ 0, 2, 0 },
        },

        // Corner up to right
        new int[][]{
            new int[]{ 0, 1, 0 },
            new int[]{ 1, 2, 2 },
            new int[]{ 0, 2, 0 },
        },

        // Corner up to left
        new int[][]{
            new int[]{ 0, 1, 0 },
            new int[]{ 2, 2, 1 },
            new int[]{ 0, 2, 0 },
        },

        // End from the left
        new int[][]{
            new int[]{ 0, 1, 0 },
            new int[]{ 2, 2, 1 },
            new int[]{ 0, 1, 0 },
        },

        // Top to the right
        new int[][]{
            new int[]{ 0, 2, 0 },
            new int[]{ 1, 2, 2 },
            new int[]{ 0, 1, 0 },
        },

        // Top to the left
        new int[][]{
            new int[]{ 0, 2, 0 },
            new int[]{ 2, 2, 1 },
            new int[]{ 0, 1, 0 },
        },

        // End from the right
        new int[][]{
            new int[]{ 0, 1, 0 },
            new int[]{ 1, 2, 2 },
            new int[]{ 0, 1, 0 },
        },

        // End from the top
        new int[][]{
            new int[]{ 0, 2, 0 },
            new int[]{ 1, 2, 1 },
            new int[]{ 0, 1, 0 },
        },

        // End from the bottom
        new int[][]{
            new int[]{ 0, 1, 0 },
            new int[]{ 1, 2, 1 },
            new int[]{ 0, 2, 0 },
        }
    };

    public RoadEntity(GameState game) {
        super(game);
        this.update();
    }


    @Override
    public void update() {
        boolean neighbors[][] = new boolean[3][3];

        for ( int y = 0; y < 3; y++ ) {
            for ( int x = 0; x < 3; x++ ) {
                Entity neighbor = this.game.getTile(x - 1 + getX(), y - 1 + getY());

                if ( neighbor != null && neighbor instanceof RoadEntity ) {
                    neighbors[x][y] = true;
                }
                else {
                    neighbors[x][y] = false;
                }
            }
        }

        for ( int i = 0; i < roadConfigurations.length; i++ ) {
            boolean good = true;

            for ( int y = 0; y < 3; y++ ) {
                for ( int x = 0; x < 3; x++ ) {
                    if ( roadConfigurations[i][x][y] == 1 && neighbors[x][y] ) {
                        good = false;
                    }

                    if ( roadConfigurations[i][x][y] == 2 && !neighbors[x][y] ) {
                        good = false;
                    }
                }
            }

            if ( good ) {
                setImage(roadImages[i]);
                return;
            }
        }

        setImage(defaultImage);
    }
}
