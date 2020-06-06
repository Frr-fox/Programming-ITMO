package Common.StudyGroup;

import java.io.Serializable;

/**
 * Класс для описания старосты учебной группы Client.Common.Client.Commands.Commands.Common.StudyGroup,
 * обладающий свойствами name, height, eyeColor, hairColor, nationality, location
 * @author Нечкасова Олеся
 */

public class Person implements Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Long height; //Значение поля должно быть больше 0, Поле может быть null
    private Color eyeColor; //Поле может быть null
    private Color hairColor; //Поле может быть null
    private Country nationality; //Поле не может быть null
    private Location location; //Поле может быть null

    /**
     * Конструктор для создания новых объектов типа Person
     * без параметров
     */
    public Person(){}

    /**
     * Конструктор для создание новых объектов типа Person
     * @param name задает имя админа
     * @param height задает рост админа
     * @param eyeColor задает цвет глаз админа
     * @param hairColor задает цвет волос админа
     * @param nationality задает национальность админа
     * @param z задает координату х места
     * @param w задает координату y места
     * @param nameLocation задает название места
     */
    public Person(String name, Long height, Color eyeColor, Color hairColor, Country nationality, Float z, Double w, String nameLocation) {
        this.name = name;
        this.height = height;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
        this.location = new Location(z, w, nameLocation);
    }

    /**
     * Конструктор для создание новых объектов типа Person
     * @param name задает имя админа
     * @param height задает рост админа
     * @param eyeColor задает цвет глаз админа
     * @param hairColor задает цвет волос админа
     * @param nationality задает национальность админа
     * @param location задает местоположение админа
     */
    public Person(String name, Long height, Color eyeColor, Color hairColor, Country nationality, Location location) {
        this.name = name;
        this.height = height;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public Long getHeight() {
        return height;
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public Color getHairColor() {
        return hairColor;
    }

    public Country getNationality() {
        return nationality;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        if (location == null){
            return "name = " + name + ", height = " + height + ", eye color = " + eyeColor + ", hair color = " + hairColor
                    + ", nationality = " + nationality + ", location: null";
        } else {
            return "name = " + name + ", height = " + height + ", eye color = " + eyeColor + ", hair color = " + hairColor
                    + ", nationality = " + nationality + ", location: " + location.toString();
        }
    }
}