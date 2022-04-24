package application;


import javafx.scene.image.ImageView;

public abstract class Tile {
	private int id;
	private String property;
	private ImageView image;
	private int rowIndex;
	private int columnIndex;
	public Tile(int id, String property, ImageView image) {
		this.setId(id);
		this.setProperty(property);
		this.setImage(image);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public ImageView getImage() {
		return image;
	}
	public void setImage(ImageView image) {
		this.image = image;
	}
	public int getRowIndex() {
		return rowIndex;
	}
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}
	public int getColumnIndex() {
		return columnIndex;
	}
	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}

	
}
