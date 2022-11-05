package com.realgotqkura.engine;

import com.realgotqkura.entities.GameModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scene {

    public static Map<Mesh, List<GameModel>> meshMap = new HashMap<>();

    public static void setGameItems(GameModel[] gameItems) {
        int numGameItems = gameItems != null ? gameItems.length : 0;
        for (int i=0; i<numGameItems; i++) {
            addGameItem(gameItems[i]);
        }
    }

    public static void addGameItem(GameModel model){
        Mesh mesh = model.getMesh();
        List<GameModel> list = meshMap.get(mesh);
        if (list == null) {
            list = new ArrayList<>();
            meshMap.put(mesh, list);
        }
        list.add(model);
    }
}
