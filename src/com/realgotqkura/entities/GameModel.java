package com.realgotqkura.entities;

import com.realgotqkura.engine.Mesh;
import com.realgotqkura.engine.Scene;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class GameModel {

    private final Mesh mesh;

    private final Vector3f position;

    private float scale;

    private final Vector3f rotation;

    private boolean insideFrustum;

    private boolean disableFrustumCulling;


    public static List<GameModel> gameModels = new ArrayList<>();
    public static List<Block> blocks = new ArrayList<>();

    private boolean visible = true;
    public GameModel(Mesh mesh, Vector3f position) {
        this.mesh = mesh;

        this.position = position;
        scale = 1f;
        rotation = new Vector3f(0, 0, 0);
        insideFrustum = true;
        disableFrustumCulling = false;
        gameModels.add(this);
        Scene.addGameItem(this);
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(float x, float y, float z) {
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;
    }


    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(float x, float y, float z) {
        this.rotation.x = x;
        this.rotation.y = y;
        this.rotation.z = z;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public boolean isInsideFrustum() {
        return insideFrustum;
    }

    public void setInsideFrustum(boolean insideFrustum) {
        this.insideFrustum = insideFrustum;
    }

    public boolean isDisableFrustumCulling() {
        return disableFrustumCulling;
    }

    public void setDisableFrustumCulling(boolean disableFrustumCulling) {
        this.disableFrustumCulling = disableFrustumCulling;
    }

    public boolean getVisibility(){
        return visible;
    }

    public void setVisible(boolean visible){
        this.visible = visible;
    }
}
