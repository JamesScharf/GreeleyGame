public class GameState {
	private Entity array[][];
	private int width;
	private int height;

	public GameState(int width, int height) {
		this.array = new Entity[width][height];

		this.width = width;
		this.height = height;
	}

	public Entity set(int x, int y, Entity entity) {
		if ( x < 0 || x >= this.width || y < 0 || y >= this.height ) {
			return null;
		}

		if ( this.array[x][y] == null ) {
			this.array[x][y] = entity;
			return null;
		}
		else {
			if ( !this.array[x][y].isCollidable() ) {
				Entity tmp = this.array[x][y];
				this.array[x][y] = entity;
				return tmp;
			}
			else {
				return null;
			}
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
				this.array[x][y].update();
			}
		}
	}
}
