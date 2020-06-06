package Common.StudyGroup;

import java.io.Serializable;

/**
 * Класс для задания координат Coordinates,
 * обладающий свойствами x, y
 * @author Нечкасова Олеся
 */

public class Coordinates implements Serializable {
    private Float x; //Поле не может быть null
    private Integer y; //Поле не может быть null, Максимальное значение поля: 911

    /**
     * Конструктор для создания новых объектов типа Location
     * без параметров
     */
    public Coordinates(){}

    /**
     * Конструктор для создания новых объектов типа Coordinates
     * @param x задает координату х
     * @param y задает координату y
     */
    public Coordinates(Float x, Integer y){
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return (double)x;
    }

    public Integer getY() {
        return y;
    }


    public void setX(Float x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "x=" + x + " y=" + y;
    }
}