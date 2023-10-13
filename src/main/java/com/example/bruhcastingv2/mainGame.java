package com.example.bruhcastingv2;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.io.IOException;
import static com.example.bruhcastingv2.spriteO.*;

public class mainGame extends Application {
    player Player = new player();
    rayCasting rayCasting = new rayCasting();
    private long lastNanoTime;
    Circle player = new Circle();
    spriteO spriteObject = new spriteO();


    @Override
    public void start(Stage stage) throws IOException {
        Canvas canvas = new Canvas(1280, 720);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        AnchorPane root = new AnchorPane(canvas);
        Scene scene = new Scene(root, 1280, 720);
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.show();
        root.getChildren().add(player);
        root.getChildren().add(imageView);

        lastNanoTime = System.nanoTime();
        // Start the game loop using AnimationTimer
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                // Calculate elapsed time since the last frame
                long elapsedTime = currentNanoTime - lastNanoTime;
                double deltaTime = elapsedTime / 1e9; // Convert nanoseconds to seconds

                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

                lastNanoTime = currentNanoTime;

                draw(root, gc, player);
                update(scene, deltaTime, gc, root);
            }
        }.start();
    }

    public void update(Scene scene, double deltaTime, GraphicsContext gc, AnchorPane root) {
        Player.update(scene, deltaTime);
        rayCasting.rayCast(gc);
        spriteObject.getSprite(root);
    }

    public void draw(AnchorPane root, GraphicsContext gc, Circle player) {
//        Player.drawPlayer(root, gc, player);
    }

    public static void main(String[] args) {
        launch();
    }
}