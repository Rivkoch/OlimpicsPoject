//Rivka Doskoch & Yuval Terry 
package YandR_Olympics;

import YandR_Olympics.Controller.Controller;
import YandR_Olympics.Model.OlympicsSystemManagement;
import YandR_Olympics.View.OlympicsView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Program extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		OlympicsView theView = new OlympicsView(primaryStage);
		OlympicsSystemManagement theModel = new OlympicsSystemManagement();
		new Controller(theModel,theView);
	}
}