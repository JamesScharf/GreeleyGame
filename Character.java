import javafx.scene.image.Image;

public class Character extends Entity { //Both for players and nonplayers
	private boolean isPlayer;
	private boolean canMove;
	private static Image characterImage = new Image("assets/images/Unit/medievalUnit_05.png");
	private int health = 100;

	public Character(GameState game) {
		super(game, characterImage);
	}

	public void moveRight() {
		int x = getX();
		int y = getY();


		if ( this.game.setObject(x, y + 1, this) ) {
			this.game.removeObject(x, y);
		}
	}

	public void moveLeft() {
		int x = getX();
		int y = getY();

		if ( this.game.setObject(x, y - 1, this) ) {
			this.game.removeObject(x, y);
		}
	}

	public void moveUp() {
		int x = getX();
		int y = getY();

		if ( this.game.setObject(x - 1, y, this) ) {
			this.game.removeObject(x, y);
		}
	}

	public void moveDown() {
		int x = getX();
		int y = getY();

		if ( this.game.setObject(x + 1, y, this) ) {
			this.game.removeObject(x, y);
		}
	}

	public void isPlayer(boolean player) {
		this.isPlayer = player;
	}

	public void canMove(boolean canPlayerMove) {
		this.canMove = canPlayerMove;
	}

	public int getHealth() {
		return health;
	}

	public void updateHealth(int newHealth) {
		health = newHealth;
	}

	@Override
	public void update() {

	}
}
