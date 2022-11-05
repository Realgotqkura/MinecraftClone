package com.realgotqkura.utilities;

import com.realgotqkura.main.DisplayManager;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.glfwGetKey;

public class Input {

    public static boolean isKeyPressed(int keyCode) {
        return glfwGetKey(DisplayManager.getWindow(), keyCode) == GLFW_PRESS;
    }


}
