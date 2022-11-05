package com.realgotqkura.utilities;

import java.io.*;

public class Utils {

    public static String loadShader(String fileName) {
        StringBuilder code = new StringBuilder();
        try {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));

            for(String line : br.lines().toList()){
                code.append(line).append("\n");
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return code.toString();
    }
}
