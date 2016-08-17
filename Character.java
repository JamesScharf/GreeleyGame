public class Character extends Entity { //Both for players and nonplayers
	private boolean isPlayer;
	private boolean canMove;
	public void moveRight() {
		int x = this.game.getX();
		int y = this.game.getY();
		this.game.setXY(x+1, y);
	}
	public void moveLeft() {
		int x = this.game.getX();
		int y = this.game.getY();
		this.game.setXY(x-1, y);
	}
	public void moveUp() {
		int x = this.game.getX();
		int y = this.game.getY();
		this.game.setXY(x, y+1);
	}
	public void moveDown() {
		int x = this.game.getX();
		int y = this.game.getY();
		this.game.setXY(x, y-1);
	}
	public void isPlayer(boolean player) {
		this.isPlayer = player;
	}
	public void canMove(boolean canPlayerMove) {
		this.canMove = canPlayerMove;
	}
}
