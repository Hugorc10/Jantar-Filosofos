/*
 * Created by Hugo Teixeira Mafra <hugorc10@hotmail.com> on 07/09/2018. Last modification on 10/09/2018.
 * <p>
 * Enrollment number: 201611540
 * <p>
 * Dining philosophers problem is an example problem often used in concurrent algorithm 
 * design to illustrate synchronization issues and techniques for resolving them. 
 * <p>
 * Dining philosophers problem is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * Dining philosophers problem is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.Table;
import view.ScreenView;

public class Principal extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        ScreenView sv = new ScreenView();
        
        Scene scene = new Scene(sv.createContent());
        
        ScreenView.startBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ScreenView.startBtn.setDisable(true);
                Table table = new Table();
                table.setDaemon(true);
                table.start();
            }
        });
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Dining philosophers problem");
        stage.setResizable(false);
        stage.sizeToScene();
        stage.show();
    }
}
