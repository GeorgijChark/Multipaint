package graphics;

import java.awt.*;
import java.util.ArrayList;

public class Command {
    private ArrayList<Object> arguments;
    private String name;

    public Command(String name) {
        this.name = name;
        arguments = new ArrayList<>();
    }

    public int getNumberOfArguments() {
        switch (name) {
            case "line":
                return 9;
        }

        return 0;
    }

    public void addArgument(Object o) {
        arguments.add(o);
    }

    public void addArguments(Object[] objects) {
        for (Object object : objects) {
            addArgument(object);
        }
    }

    public void perform() {
        if (name.equals("line")) {
            new Line(new int[]{(int) arguments.get(1), (int) arguments.get(2)},
                    new int[]{(int) arguments.get(3), (int) arguments.get(4)},
                    (int) arguments.get(5), new Color((int) arguments.get(6), (int) arguments.get(7), (int) arguments.get(8))).draw((Graphics) arguments.get(0));
        }
    }
}
