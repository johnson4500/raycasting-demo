package com.example.bruhcastingv2;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import static com.example.bruhcastingv2.settings.*;

public class player {
    boolean lookLeft;
    boolean lookRight;
    boolean moveUp;
    boolean moveDown;
    boolean moveLeft;
    boolean moveRight;


    public void drawPlayer(AnchorPane root, GraphicsContext gc, Circle player) {
        player.setFill(Color.RED);
        player.setRadius(5);
        player.setCenterX(playerX * 64 + 5);
        player.setCenterY(playerY * 65.45 + 3);
        gc.setStroke(Color.SKYBLUE);
        gc.strokeLine(playerX * 64, playerY * 64, (playerX * 64) + (1000 * Math.cos(playerAngle)), (playerY * 64) + (1000 * Math.sin(playerAngle)));

    }

    public void movement(Scene scene, double deltaTime) {
        scene.setOnKeyPressed(event -> handleKeyPress(event.getCode()));
        scene.setOnKeyReleased(event -> handleKeyRelease(event.getCode()));
        double sin = Math.sin(playerAngle);
        double cos = Math.cos(playerAngle);

        double dx = 0;
        double dy = 0;
        double speed = settings.playerSpeed;

        double speedSin = speed * sin;
        double speedCos = speed * cos;

        if (moveUp) {
            dx += speedCos;
            dy -= speedSin;
        }
        if (moveDown) {
            dx -= speedCos;
            dy += speedSin;
        }
        if (moveLeft) {
            dx += speedSin;
            dy += speedCos;
        }
        if (moveRight) {
            dx -= speedSin;
            dy -= speedCos;
        }
        if (lookLeft) {
            playerAngle -= settings.playerRotationSpeed;
        }
        if (lookRight) {
            playerAngle += settings.playerRotationSpeed;
        }

        wallCollisions(dx, dy);
        playerAngle %= (2 * Math.PI);
    }


    public void update(Scene scene, double deltaTime) {
        movement(scene, deltaTime);
    }

    public boolean checkWall(int dx, int dy) {
        return map.worldMap[dy][dx] == 0;
    }

    public void wallCollisions(double dx, double dy) {
        if (checkWall((int) (playerX + dx), (int) (playerY))) {
            playerX += dx;
        }

        if (checkWall((int) (playerX), (int) (playerY - dy))) {
            playerY -= dy;
        }
    }

    private void handleKeyPress(KeyCode code) {
        // Set the corresponding movement flag to true when a key is pressed
        switch (code) {
            case LEFT:
                lookLeft = true;
                break;
            case RIGHT:
                lookRight = true;
                break;
            case W:
                moveUp = true;
                break;
            case A:
                moveLeft = true;
                break;
            case S:
                moveDown = true;
                break;
            case D:
                moveRight = true;
                break;
            default:
                break;
        }
    }
    private void handleKeyRelease(KeyCode code) {
        // Set the corresponding movement flag to false when a key is released
        switch (code) {
            case LEFT:
                lookLeft = false;
                break;
            case RIGHT:
                lookRight = false;
                break;
            case W:
                moveUp = false;
                break;
            case A:
                moveLeft = false;
                break;
            case S:
                moveDown = false;
                break;
            case D:
                moveRight = false;
                break;
            default:
                break;
        }
    }
}
