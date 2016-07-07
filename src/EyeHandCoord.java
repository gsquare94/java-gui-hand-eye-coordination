import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class EyeHandCoord extends Application{
	private Pane pane;			//base layout pane
	private Circle cir;			//the  circle to be drawn
	private long start_time;	//time when program execution begins
	private long stop_time;		//time when program execution stops
	private int count;			//to keep count of number of attempts
	
	@Override
	public void start(Stage stage) throws Exception {
		pane = new Pane();		//initialize pane
		cir = new Circle(10);	//create circle of radius 10 pixels
		
	    pane.getChildren().add(cir);
	    cir.setOnMouseClicked(new EventHandler<MouseEvent>() {	//add mouse event handler as anonymous inner class

			@Override
			public void handle(MouseEvent me) {
				count++;			//increment count 
				if(count<5){
					draw_circle();	// redraw the circles for 5 tries
				}
				else{
					pane.getChildren().remove(cir);		//else delete the circle and replace it with a text reading the time spent on the program
					stop_time = System.currentTimeMillis();
					Text text = new Text((stop_time - start_time)+" milliseconds");
					text.setX(pane.getWidth()/2);
					text.setY(pane.getHeight()/2);
					pane.getChildren().add(text);
				}
			}
		});
	    stage.setTitle("Eye Hand Coordination");		//set the title and display the pane
		stage.setScene(new Scene(pane,500, 500));
		stage.show();
		
		start_time = System.currentTimeMillis();		//begin countdown
		draw_circle();									//draw the first circle
	}
	private void draw_circle() {
		double h = Math.random() * 360;				//create color from random hue, saturation and brightness values
		double s = Math.random() * 0.5 + 0.25;
	    double b = Math.random() * 0.5 + 0.5;
	    cir.setFill(Color.hsb(h, s, b));		//set circle with created color
	    cir.setCenterX((pane.getWidth() - 2 * cir.getRadius()) * Math.random()  + cir.getRadius());	//set circle center at random position within bounds
	    cir.setCenterY((pane.getHeight() - 2 * cir.getRadius()) * Math.random() + cir.getRadius());
	}
	public static void main(String[] args) {
	    Application.launch(args);
	}
}
