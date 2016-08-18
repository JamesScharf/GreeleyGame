public class GameState {
	private Entity objectMap[][];
	private Entity tileMap[][];

	private int width;
	private int height;

	private Character primaryCharacter;

	public GameState(int width, int height) {
		this.tileMap = new Entity[width][height];
		this.objectMap = new Entity[width][height];

		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean setTile(int x, int y, Entity entity) {
		if ( x < 0 || x >= this.width || y < 0 || y >= this.height ) {
			return false;
		}

		if ( this.tileMap[x][y] == null ) {
			this.tileMap[x][y] = entity;
			entity.setXY(x, y);
			return true;
		}
		else {
			return false;
		}
	}

	public Entity getTile(int x, int y) {
		if ( x < 0 || x >= this.width || y < 0 || y >= this.height ) {
			return null;
		}

		return this.tileMap[x][y];
	}

	public void removeObject(int x, int y) {
		if ( x < 0 || x >= this.width || y < 0 || y >= this.height ) {
			return;
		}

		this.objectMap[x][y] = null;
	}

	public boolean setObject(int x, int y, Entity entity) {
		if ( x < 0 || x >= this.width || y < 0 || y >= this.height ) {
			return false;
		}

		if ( this.objectMap[x][y] == null ) {
			this.objectMap[x][y] = entity;

			if ( entity != null ) {
				entity.setXY(x, y);
			}

			return true;
		}
		else {
			return false;
		}
	}

	public Entity getObject(int x, int y) {
		if ( x < 0 || x >= this.width || y < 0 || y >= this.height ) {
			return null;
		}

		return this.objectMap[x][y];
	}

	public void setPrimaryCharacter(Character character) {
		this.primaryCharacter = character;
	}

	public Character getPrimaryCharacter() {
		return this.primaryCharacter;
	}

	public void update() {
		for ( int x = 0; x < this.width; x++ ) {
			for ( int y = 0; y < this.height; y++ ) {
				if ( this.objectMap[x][y] != null ) {
					this.objectMap[x][y].update();
				}

				if ( this.tileMap[x][y] != null ) {
					this.tileMap[x][y].update();
				}
			}
		}
	}
}
