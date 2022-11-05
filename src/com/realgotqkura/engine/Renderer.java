package com.realgotqkura.engine;

import com.realgotqkura.entities.Block;
import com.realgotqkura.entities.Camera;
import com.realgotqkura.entities.GameModel;
import com.realgotqkura.main.DisplayManager;
import com.realgotqkura.main.Main;
import com.realgotqkura.shaders.ShaderProgram;
import com.realgotqkura.utilities.OpenGLUtils;
import com.realgotqkura.utilities.ShapeVertexArrays;
import com.realgotqkura.utilities.Utils;
import org.joml.Matrix4f;
import org.lwjgl.opengl.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.lwjgl.opengl.GL15.*;

public class Renderer {

    private ShaderProgram shaderProgram;
    private int vaoID;
    private int vboID;
    private int query_id;

    public static final float FOV = (float) Math.toRadians(60.0f);

    public static final float Z_NEAR = 0.001f;

    public static final float Z_FAR = 100000f;

    private Matrix4f projectionMatrix;
    private Transformation transformation;
    private FrustumCullingFilter frustumFilter;
    private List<GameModel> filteredItems;

    public Renderer() {
        transformation = new Transformation();
        frustumFilter = new FrustumCullingFilter();
        filteredItems = new ArrayList<>();
    }

    public void init() throws Exception {
        shaderProgram = new ShaderProgram();
        shaderProgram.createVertexShader(Utils.loadShader("src/com/realgotqkura/shaders/vertex.vs"));
        shaderProgram.createFragmentShader(Utils.loadShader("src/com/realgotqkura/shaders/fragment.fs"));
        shaderProgram.link();
        query_id = glGenQueries();

        float aspectRatio = (float) DisplayManager.WIDTH / DisplayManager.HEIGHT;
        projectionMatrix = new Matrix4f().perspective(FOV, aspectRatio,
                Z_NEAR, Z_FAR);
        shaderProgram.createUniform("projectionMatrix");
        shaderProgram.createUniform("modelViewMatrix");
        shaderProgram.createUniform("texture_sampler");
        shaderProgram.setUniform("projectionMatrix", projectionMatrix);
    }

    public void render(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        shaderProgram.bind();
        Map<Mesh, List<GameModel>> mapMeshes = Scene.meshMap;
        //frustumFilter.filter(scene.getGameInstancedMeshes());
        transformation.updateProjectionMatrix(Renderer.FOV, DisplayManager.WIDTH, DisplayManager.HEIGHT, Renderer.Z_NEAR, Renderer.Z_FAR);
        transformation.updateViewMatrix(Main.camera);
        Matrix4f projectionMatrix = transformation.getProjectionMatrix();
        shaderProgram.setUniform("projectionMatrix", projectionMatrix);

        Matrix4f viewMatrix = transformation.getViewMatrix();
        shaderProgram.setUniform("texture_sampler", 0);
        frustumFilter.updateFrustum(projectionMatrix, transformation.getViewMatrix());
        frustumFilter.filter(mapMeshes);

        /*
        for (GameModel gameItem : GameModel.gameModels) {
            // Set model view matrix for this item
            Matrix4f modelViewMatrix = transformation.getModelViewMatrix(gameItem, viewMatrix);
            shaderProgram.setUniform("modelViewMatrix", modelViewMatrix);
            // Render the mes for this game item
            gameItem.getMesh().renderMesh();
        }

        //*/
        filteredItems.clear();
        for (GameModel gameItem : GameModel.gameModels) {
            if (gameItem.isInsideFrustum()) {
                if(gameItem.getVisibility()){
                    filteredItems.add(gameItem);
                }
            }
        }
        //System.out.println(filteredItems.size());
        for (GameModel gameModel : filteredItems) {
            // Set model view matrix for this item
            Matrix4f modelViewMatrix = transformation.getModelViewMatrix(gameModel, viewMatrix);
            shaderProgram.setUniform("modelViewMatrix", modelViewMatrix);
            // Render the mes for this game item
            gameModel.getMesh().render();
        }


        shaderProgram.unbind();
    }



    public void cleanUp(){
        glDeleteQueries(query_id);
        if (shaderProgram != null) {
            shaderProgram.cleanup();
        }

    }
}
