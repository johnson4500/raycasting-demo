package com.example.bruhcastingv2;

public class settings {
    public static int WIDTH = 1280;
    public static int HEIGHT = 720;
    public static int HALF_WIDTH = WIDTH / 2;
    public static int HALF_HEIGHT = HEIGHT / 2;
    public static int FPS = 60;
    public static double playerX = 2;
    public static double playerY = 2;
    public static double playerAngle = 0;
    public static double playerSpeed = 0.04;
    public static double playerRotationSpeed = 0.015;
    public static double FOV = Math.PI / 3;
    public static double half_FOV = FOV / 2;
    public static int numRays = (int) Math.floor(WIDTH / 2);
    public static int halfNumRays = numRays / 2;
    public static double deltaAngle = FOV / numRays;
    public static double maxDepth = 20;
    public static double screenDistance = HALF_WIDTH / Math.tan(half_FOV);
    public static int scale = (int) Math.floor(WIDTH / numRays);
}