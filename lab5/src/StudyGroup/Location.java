package StudyGroup;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Класс для описания местоположения объектов типа StudyGroup,
 * обладающий свойствами x, y, name
 * @author Нечкасова Олеся
 */

@XmlRootElement
public class Location {
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

    @XmlElement(name = "z")
    public void setX(float x) {
        this.x = x;
    }

    @XmlElement(name = "w")
    public void setY(Double y) {
        this.y = y;
    }

    @XmlElement(name = "nameLocation")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
            return "x=" + x + ", y=" + y + ", Location's name = " + name ;
    }
}