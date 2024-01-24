package main;

import java.util.HashMap;
import java.util.Map;

public class FlyWeightFactory {
    private static Map<String, Shape> shapes = new HashMap<>();

    public static Shape getShape(String type) {
        Shape shape = shapes.get(type); 

        if (shape == null) {       
            if (type.equals("X")) {
                shapes.put(type, new XShape());
            } else if (type.equals("O")) {
                shapes.put(type, new OShape());
            }
        }

        return shapes.get(type);
    }
}
