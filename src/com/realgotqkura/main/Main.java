package com.realgotqkura.main;

import com.realgotqkura.engine.*;
import com.realgotqkura.entities.Block;
import com.realgotqkura.entities.Camera;
import com.realgotqkura.entities.GameModel;
import com.realgotqkura.shaders.ShaderProgram;
import com.realgotqkura.utilities.*;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjglx.test.spaceinvaders.Game;


import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public class Main {

    private final long window;
    private final ShaderProgram program;
    private final Renderer renderer;
    private Mesh blockMesh;
    public static Camera camera;
    private MouseInput mouseInput;
    private Transformation transformation;


    public Main(){
        DisplayManager.start();
        window = DisplayManager.getWindow();
        program = new ShaderProgram();
        renderer = new Renderer();
        camera = new Camera();
        mouseInput = new MouseInput();
        transformation = new Transformation();
        Texture texture = new Texture("grassblock");
        blockMesh = new Mesh(ShapeVertexArrays.quadPositions, ShapeVertexArrays.quadIndices, ShapeVertexArrays.textCoords, texture);
        for(int i = 1; i <= 16; i++){
            for(int j = 1; j <= 16; j++){
                for(int k = 1; k <= 16; k++){
                    Block blk = new Block(new GameModel(blockMesh, new Vector3f(i,j,k)),new BlockLocation(i,j,k));
                }
            }
        }
        try {
            renderer.init();
            mouseInput.init();
        }catch(Exception e){
            e.printStackTrace();
        }
        run();
    }


    public void run(){
        double maxFPS = 1 / 144F; //144fps max
        double previous = GameSys.getTime();
        double steps = 0.0;
        //FPS counter
        double previousTime = glfwGetTime();
        int frameCount = 0;
        while(!glfwWindowShouldClose(window)){
            double loopStartTime = GameSys.getTime();
            double elapsed = loopStartTime - previous;
            previous = GameSys.getTime();
            steps += elapsed;
            // * FPS COUNTER START*
            // Measure speed
            double currentTime = glfwGetTime();
            frameCount++;
            // If a second has passed.
            if ( currentTime - previousTime >= 1.0)
            {
                // Display the frame count here any way you want.
                GLFW.glfwSetWindowTitle(window, "Minecraft Clone FPS: " + frameCount);

                frameCount = 0;
                previousTime = currentTime;
            }
            // * FPS COUNTER END*
            //Handle Input
            input();
            //System.out.println(GameModel.gameModels.size());
            while (steps >= maxFPS) {
                //DON'T DO STUFF HERE PLEASE
                steps -= maxFPS;
            }
            //Render different Stuff
            renderer.render();
            DisplayManager.update();
            sync(GameSys.getTime());
        }
        cleanUpAll();
    }

    protected void input() {
        mouseInput.input();
        mouseInput.change(camera);
        mouseInput.update(camera);
    }

    private void cleanUpAll(){
        program.cleanup();
        renderer.cleanUp();
    }




    public static void main(String[] args) {
        new Main();
    }

    private void sync(double loopStartTime) {
        float loopSlot = 1f / 50;
        double endTime = loopStartTime + loopSlot;
        while(GameSys.getTime() < endTime) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ignored) {}
        }
    }
}
