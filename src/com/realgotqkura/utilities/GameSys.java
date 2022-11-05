package com.realgotqkura.utilities;

import org.lwjgl.glfw.GLFW;
import org.lwjglx.LWJGLUtil;

import javax.swing.*;
import java.awt.*;
import java.net.URI;

public class GameSys {


    public static String getVersion() {
        return GLFW.glfwGetVersionString();
    }

    public static long getTimerResolution() {
        return 1000L;
    }

    public static long getTime() {
        return (long)(GLFW.glfwGetTime() * 1000.0);
    }

    public static long getNanoTime() {
        return (long)(GLFW.glfwGetTime() * 1.0E9);
    }

    public static boolean openURL(String url) {
        if (!Desktop.isDesktopSupported()) {
            return false;
        } else {
            Desktop desktop = Desktop.getDesktop();
            if (!desktop.isSupported(Desktop.Action.BROWSE)) {
                return false;
            } else {
                try {
                    desktop.browse(new URI(url));
                    return true;
                } catch (Exception var3) {
                    return false;
                }
            }
        }
    }

    public static void alert(String title, String message) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception var3) {
            LWJGLUtil.log("Caught exception while setting LAF: " + var3);
        }

        JOptionPane.showMessageDialog((Component)null, message, title, 2);
    }
}
