import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Tile extends ImageView {
	private Image image;
	public int rowIndex;
	public int columnIndex;
	private int x;
	private int y;
	public Tile(Image image) {
		super(image);
	}
	public int getXLocation() {
		return x;
	}
	public int getYLocation() {
		return y;
	}
	public void setXLocation(int x) {
		this.x=x;
	}
	public void setYLocation(int y) {
		this.y=y;
	}
	
}
