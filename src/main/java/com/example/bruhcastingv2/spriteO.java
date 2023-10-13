package com.example.bruhcastingv2;

import static com.example.bruhcastingv2.settings.*;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileInputStream;


public class spriteO {
    double spriteX = 6;
    double spriteY = 6;
    static Image textureImage = new Image(new File("C:\\Users\\johns\\Downloads\\goodness.png").toURI().toString());
    public static ImageView imageView = new ImageView(textureImage);
    int width = (int) textureImage.getWidth();
    int height = (int) textureImage.getHeight();
    int halfWidth = width / 2;
    double ratio = width / height;

    double dx = 0;
    double dy = 0;
    double theta = 0;
    double screenX = 0;
    double distance = 1;

    public void spriteProjection(double distance, double screenX) {
        double projection = screenDistance / distance;
        double projectionWidth = projection * ratio;
        double projectionHeight = projection;

        double spriteHalfWidth = projectionWidth / 2;
        double positionX = screenX - spriteHalfWidth;
        double positionY = HALF_HEIGHT - Math.floor(projectionHeight / 2);

        imageView.setFitHeight(projectionHeight);
        imageView.setFitWidth(projectionWidth);
        imageView.setX(positionX);
        imageView.setY(positionY);
        imageView.setPreserveRatio(true);
    }

    public void getSprite(AnchorPane root) {
        dx = spriteX - playerX;
        dy = spriteY - playerY;
        theta = Math.atan2(dy, dx);

        double delta = theta - playerAngle;
        if ((dx > 0 && playerAngle > Math.PI) || (dx < 0 && dy < 0)) {
            delta += 2 * Math.PI;
        }

        double deltaRays = delta / deltaAngle;
        screenX = (halfNumRays + deltaRays) * scale;
        distance = Math.hypot(dx, dy) * Math.cos(delta);

        spriteProjection(distance, screenX);
    }
}
