import javafx.scene.image.Image;

public class Character extends Entity { //Both for players and nonplayers
	private boolean isPlayer;
	private boolean canMove;
	private static Image characterImage = new Image("assets/images/Unit/medievalUnit_05.png");

	public Character(GameState game) {
		super(game, characterImage);
	}

	public void moveRight() {
		int x = this.getX();
		int y = this.getY();
		this.game.setObject(x+1, y, this);
	}
	public void moveLeft() {
		int x = this.getX();
		int y = this.getY();
		this.game.setObject(x-1, y, this);
	}
	public void moveUp() {
		int x = this.getX();
		int y = this.getY();
		this.game.setObject(x, y+1, this);
	}
	public void moveDown() {
		int x = this.getX();
		int y = this.getY();
		this.game.setObject(x, y-1, this);
	}
	public void isPlayer(boolean player) {
		this.isPlayer = player;
	}
	public void canMove(boolean canPlayerMove) {
		this.canMove = canPlayerMove;
	}

	@Override
	public void update() {

	}
}
