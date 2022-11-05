package com.realgotqkura.utilities;

import com.realgotqkura.entities.Camera;
import com.realgotqkura.main.DisplayManager;
import org.joml.Vector2d;
import org.joml.Vector2f;
import org.joml.Vector3f;
import static org.lwjgl.glfw.GLFW.*;

public class MouseInput {

    private final Vector2d previousPos;

    private final Vector2d currentPos;

    private final Vector2f displVec;

    private Vector3f cameraInc;

    private boolean inWindow = false;

    private boolean leftButtonPressed = false;

    private boolean rightButtonPressed = false;
    private static final float CAMERA_POS_STEP = 0.1f;
    private static final float MOUSE_SENSITIVITY = 0.15f;


    public MouseInput() {
        previousPos = new Vector2d(-1, -1);
        currentPos = new Vector2d(0, 0);
        cameraInc = new Vector3f(0,0,0);
        displVec = new Vector2f();
    }

    public void init() {
        glfwSetInputMode(DisplayManager.getWindow(), org.lwjgl.glfw.GLFW.GLFW_CURSOR, GLFW_CURSOR_DISABLED);
        glfwSetCursorPosCallback(DisplayManager.getWindow(), (windowHandle, xpos, ypos) -> {
            currentPos.x = xpos;
            currentPos.y = ypos;
        });
        glfwSetCursorEnterCallback(DisplayManager.getWindow(), (windowHandle, entered) -> {
            inWindow = entered;
        });
        glfwSetMouseButtonCallback(DisplayManager.getWindow(), (windowHandle, button, action, mode) -> {
            leftButtonPressed = button == GLFW_MOUSE_BUTTON_1 && action == GLFW_PRESS;
            rightButtonPressed = button == GLFW_MOUSE_BUTTON_2 && action == GLFW_PRESS;
        });
    }

    public Vector2f getDisplVec() {
        return displVec;
    }

    public void input() {
        displVec.x = 0;
        displVec.y = 0;
            double deltax = currentPos.x - previousPos.x;
            double deltay = currentPos.y - previousPos.y;
            boolean rotateX = deltax != 0;
            boolean rotateY = deltay != 0;
            if (rotateX) {
                displVec.y = (float) deltax;
            }
            if (rotateY) {
                displVec.x = (float) deltay;
            }
        previousPos.x = currentPos.x;
        previousPos.y = currentPos.y;
    }

    public boolean isLeftButtonPressed() {
        return leftButtonPressed;
    }

    public boolean isRightButtonPressed() {
        return rightButtonPressed;
    }


    public void change(Camera camera) {
        cameraInc.set(0, 0, 0);
        if (Input.isKeyPressed(GLFW_KEY_W)) {
            cameraInc.z = -1;
            cameraInc.y = (float) Math.sin(Math.toRadians(camera.getPitch())) * -1;
        } else if (Input.isKeyPressed(GLFW_KEY_S)) {
            cameraInc.z = 1;
        }
        if (Input.isKeyPressed(GLFW_KEY_A)) {
            cameraInc.x = -1;
        } else if (Input.isKeyPressed(GLFW_KEY_D)) {
            cameraInc.x = 1;
        }
        if (Input.isKeyPressed(GLFW_KEY_LEFT_SHIFT)) {
            cameraInc.y = -1;
        } else if (Input.isKeyPressed(GLFW_KEY_SPACE)) {
            cameraInc.y = 1;
        }
    }

    public void update(Camera camera) {
        // Update camera position
        camera.movePosition(cameraInc.x * CAMERA_POS_STEP, cameraInc.y * CAMERA_POS_STEP, cameraInc.z * CAMERA_POS_STEP);
        // Update camera based on mouse
        Vector2f rotVec = getDisplVec();
        camera.moveRotation(rotVec.x * MOUSE_SENSITIVITY, rotVec.y * MOUSE_SENSITIVITY, 0);
        clampRot(camera);
    }

    //Clamps pitch so dude don't go spin on the camera
    public void clampRot(Camera camera){
        if(camera.getPitch() >= 90.0f){
            camera.setPitch(89.8f);
        }else if(camera.getPitch() <= -90.0f){
            camera.setPitch(-89.8f);
        }
    }
}
