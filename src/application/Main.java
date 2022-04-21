package application;
	
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane pane = new Pane();
			Circle circle = new Circle();	
			Rectangle square = new Rectangle(0,0,800,500);
			square.setFill(Color.BLACK);
			
			circle.setRadius(20);
			circle.setFill(Color.YELLOW);
			circle.setStroke(Color.YELLOWGREEN);
			pane.getChildren().add(circle);
			PathTransition pt = new PathTransition();
			Path path = new Path();
			LineTo line;
			ArcTo arcToTemp;
			path.getElements().add(new MoveTo(0.0,0.0));
			for (int i = 1; i < 10; i++) {
				if (i % 2 == 0) {
				arcToTemp = new ArcTo();
				arcToTemp.setX(50 * i);
				arcToTemp.setY(50 * i);
				arcToTemp.setRadiusX(50);
				arcToTemp.setRadiusY(50);
				path.getElements().add(arcToTemp);
				}
				else {
					line = new LineTo();
					line.setX(50.0 * i);
					line.setY(50.0 * i);
					path.getElements().add(line);
				}
			}
			pt.setPath(path);
			pt.setNode(circle);
			pt.setCycleCount(1);
			pt.setDuration(Duration.seconds(10));
			pt.autoReverseProperty();
			pt.play();
			pane.getChildren().add(square);
			Scene scene = new Scene(pane,800,500);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void animateLine(Line line,Circle circle) {
		
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}
