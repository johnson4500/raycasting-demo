package com.example.bruhcastingv2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static com.example.bruhcastingv2.settings.*;

public class rayCasting {
    public void rayCast(GraphicsContext gc) {
        double rayAngle = playerAngle - half_FOV + 0.00000001;
        double playerMapX = (int) playerX;
        double playerMapY = (int) playerY;
        double xVertIntersection;
        double yVertIntersection;
        double yHorizontalIntersection;
        double xHorizontalIntersection;
        double dx;
        double dy;
        double deltaDepth;

        for (int ray = 0; ray < numRays; ray++) {
            double sinAngle = Math.sin(rayAngle);
            double cosAngle = Math.cos(rayAngle);

            // Horizontal intersections
            if (sinAngle > 0) {
                yHorizontalIntersection = playerMapY + 1;
                dy = 1;
            } else {
                yHorizontalIntersection = playerMapY - 0.0000001;
                dy = -1;
            }

            double horizontalIntersectionDistance = (yHorizontalIntersection - playerY) / sinAngle;
            xHorizontalIntersection = playerX + horizontalIntersectionDistance * cosAngle;

            deltaDepth = dy / sinAngle;
            dx = deltaDepth * cosAngle;

            // continue to iterate through the grid lines until wall hit
            for (int i = 0; i < maxDepth; i++) {
                int gridDistX = (int) xHorizontalIntersection;
                int gridDistY = (int) yHorizontalIntersection;

                if (gridDistY > 10 || gridDistX > 19 || gridDistX < 0 || gridDistY < 0) break;
                if (map.worldMap[gridDistY][gridDistX] == 1) break;

                xHorizontalIntersection += dx;
                yHorizontalIntersection += dy;
                horizontalIntersectionDistance += deltaDepth;
            }


            // Vertical intersections, same as horizontal with few changes
            if (cosAngle > 0) {
                xVertIntersection = playerMapX + 1;
                dx = 1;
            } else {
                xVertIntersection = playerMapX - 0.0000001;
                dx = -1;
            }

            double verticalIntersectionDist = (xVertIntersection - playerX) / cosAngle;
            yVertIntersection = playerY + verticalIntersectionDist * sinAngle;

            deltaDepth = dx / cosAngle;
            dy = deltaDepth * sinAngle;



            for (int i = 0; i < maxDepth; i++) {
                int gridDistX = (int) xVertIntersection;
                int gridDistY = (int) yVertIntersection;

                if (gridDistY > 10 || gridDistX > 19 || gridDistX < 0 || gridDistY < 0) break;
                if (map.worldMap[gridDistY][gridDistX] == 1) break;
                    xVertIntersection += dx;
                    yVertIntersection += dy;
                    verticalIntersectionDist += deltaDepth;
            }

            double depth;
            if (verticalIntersectionDist < horizontalIntersectionDistance) {
                depth = verticalIntersectionDist;
            } else {
                depth = horizontalIntersectionDistance;
            }

            // fisheye effect removal
            depth *= Math.cos(playerAngle - rayAngle);
            double projectionHeight = screenDistance / (depth + 0.00000001);
            double brightness = 1.0 - Math.min(1.0, depth / 18); // Normalize distance
            gc.setFill(Color.rgb(250, 0, 0, brightness)); // Adjust brightness
            gc.fillRect(ray * scale, (HALF_HEIGHT - Math.floor(projectionHeight / 2)), scale, projectionHeight);
            rayAngle += deltaAngle;
        }
    }
}
