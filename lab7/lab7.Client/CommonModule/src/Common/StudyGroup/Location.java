package Common.StudyGroup;

import java.io.Serializable;

/**
 * Класс для описания местоположения объектов типа Client.Common.Client.Commands.Commands.Common.StudyGroup,
 * обладающий свойствами x, y, name
 * @author Нечкасова Олеся
 */

public class Location implements Serializable {
    private float x;
    private Double y; //Поле не может быть null
    private String name; //Поле не может быть null

    /**
     * Конструктор для создание новых объектов типа Location
     * @param x задает координату х места
     * @param y задает координату y места
     * @param name задает название места
     */
    public Location(float x, Double y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    /**
     * Конструктор для создания новых объектов типа Location
     * без параметров
     */
    public Location(){}

    public float getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
            return "x=" + x + ", y=" + y + ", Location's name = " + name ;
    }
}