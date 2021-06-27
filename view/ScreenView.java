package view;

import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

public class ScreenView {
    
    public static ImageView[] statusView;
    public static Text[] statusTxt;
    public static Button startBtn;
    public static Slider philosophersSld;
    
    public ScreenView() {
    
    }
    
    public Parent createContent() {
        Pane pane = new Pane();
        pane.setPrefSize(800, 650);
        
        Image tableImg = new Image(getClass().getResourceAsStream("/img/table.png"));
        
        ImageView tableView = new ImageView(tableImg);
        tableView.setFitHeight(500);
        tableView.setSmooth(true);
        tableView.setPreserveRatio(true);
        tableView.setX(150);
        tableView.setY(50);
        
        pane.getChildren().add(tableView);
        
        startBtn = new Button("Start");
        startBtn.setDefaultButton(true);
        startBtn.setPrefSize(98, 35);
        startBtn.setLayoutX(50);
        startBtn.setLayoutY(50);
        
        pane.getChildren().add(startBtn);
        
        philosophersSld = new Slider(0, 199, 100);
        philosophersSld.setPrefWidth(200);
        philosophersSld.setOrientation(Orientation.HORIZONTAL);
        philosophersSld.setShowTickMarks(true);
        philosophersSld.setMajorTickUnit(50);
        philosophersSld.setBlockIncrement(10);
        philosophersSld.setLayoutX(500);
        philosophersSld.setLayoutY(50);
        
        pane.getChildren().add(philosophersSld);
        
        Image plateImg = new Image(getClass().getResourceAsStream("/img/plate.png"));
        
        ImageView[] plateView = new ImageView[5];
        for (int i = 0; i < 5; i++) {
            plateView[i] = new ImageView();
            plateView[i].setImage(plateImg);
            plateView[i].setFitHeight(80);
            plateView[i].setSmooth(true);
            plateView[i].setPreserveRatio(true);
            
            switch (i) {
                case 0:
                    plateView[0].setX(210);
                    plateView[0].setY(240);
                    break;
                case 1:
                    plateView[1].setX(290);
                    plateView[1].setY(290);
                    break;
                case 2:
                    plateView[2].setX(410);
                    plateView[2].setY(290);
                    break;
                case 3:
                    plateView[3].setX(500);
                    plateView[3].setY(240);
                    break;
                case 4:
                    plateView[4].setX(350);
                    plateView[4].setY(200);
                    break;
            }
            
            pane.getChildren().add(plateView[i]);
        }
        
        Image[] philoshopers = new Image[5];
        for (int i = 0; i < philoshopers.length; i++) {
            philoshopers[i] = new Image(getClass().getResourceAsStream("/img/philosopher" + (i + 1) + ".png"));
        }
        
        ImageView[] philoshopersView = new ImageView[5];
        for (int i = 0; i < 5; i++) {
            philoshopersView[i] = new ImageView();
            philoshopersView[i].setImage(philoshopers[i]);
            philoshopersView[i].setFitHeight(100);
            philoshopersView[i].setCache(true);
            philoshopersView[i].setPreserveRatio(true);
            
            switch (i) {
                case 0:
                    philoshopersView[0].setX(100);
                    philoshopersView[0].setY(200);
                    break;
                case 1:
                    philoshopersView[1].setX(270);
                    philoshopersView[1].setY(375);
                    break;
                case 2:
                    philoshopersView[2].setX(420);
                    philoshopersView[2].setY(375);
                    break;
                case 3:
                    philoshopersView[3].setX(620);
                    philoshopersView[3].setY(210);
                    break;
                case 4:
                    philoshopersView[4].setX(365);
                    philoshopersView[4].setY(75);
                    break;
            }
            
            pane.getChildren().add(philoshopersView[i]);
        }
        
        statusView = new ImageView[5];
        for (int i = 0; i < 5; i++) {
            statusView[i] = new ImageView();
            statusView[i].setFitHeight(80);
            statusView[i].setPreserveRatio(true);
            statusView[i].setSmooth(true);
            
            switch (i) {
                case 0:
                    statusView[0].setX(100);
                    statusView[0].setY(350);
                    break;
                case 1:
                    statusView[1].setX(265);
                    statusView[1].setY(520);
                    break;
                case 2:
                    statusView[2].setX(420);
                    statusView[2].setY(520);
                    break;
                case 3:
                    statusView[3].setX(615);
                    statusView[3].setY(350);
                    break;
                case 4:
                    statusView[4].setX(365);
                    statusView[4].setY(0);
                    break;
            }
            
            pane.getChildren().add(statusView[i]);
            
        }
        
        statusTxt = new Text[5];
        for (int i = 0; i < 5; i++) {
            statusTxt[i] = new Text();
            statusTxt[i].setFont(Font.font("Verdana", FontPosture.REGULAR, 18));
            
            switch (i) {
                case 0:
                    statusTxt[0].setX(100);
                    statusTxt[0].setY(325);
                    break;
                case 1:
                    statusTxt[1].setX(265);
                    statusTxt[1].setY(500);
                    break;
                case 2:
                    statusTxt[2].setX(420);
                    statusTxt[2].setY(500);
                    break;
                case 3:
                    statusTxt[3].setX(615);
                    statusTxt[3].setY(335);
                    break;
                case 4:
                    statusTxt[4].setX(365);
                    statusTxt[4].setY(200);
                    break;
            }
            
            pane.getChildren().add(statusTxt[i]);
            
        }
        
        return pane;
    }
}
