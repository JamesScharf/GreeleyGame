public class GameState {
	private Entity array[][];
	private int width;
	private int height;

	public GameState(int width, int height) {
		this.array = new Entity[width][height];

		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean set(int x, int y, Entity entity) {
		if ( x < 0 || x >= this.width || y < 0 || y >= this.height ) {
			return false;
		}

		if ( this.array[x][y] == null ) {
			this.array[x][y] = entity;
			entity.setXY(x, y);
			return true;
		}
		else {
			return false;
		}
	}

	public Entity get(int x, int y) {
		if ( x < 0 || x >= this.width || y < 0 || y >= this.height ) {
			return null;
		}

		return this.array[x][y];
	}

	public void update() {
		for ( int x = 0; x < this.width; x++ ) {
			for ( int y = 0; y < this.height; y++ ) {
				if ( this.array[x][y] != null ) {
					this.array[x][y].update();
				}
			}
		}
	}
}
