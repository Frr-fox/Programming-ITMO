package Common.StudyGroup;

import java.io.Serializable;

/**
 * Перечисляемый тип для задания значений nationality
 * у объектов типа Person
 * @author Нечкасова Олеся
 */

public enum Country implements Serializable {
    RUSSIA("Русский"),
    SPAIN("Испанец"),
    ITALY("Итальянец");

    /** Поле название константы**/
    private String title;

    /**
     * Конструктор для константных значений
     * @param title строковая перменная для обозначения нациаональности
     */
    Country(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    /**
     * Метод для парсинга переменной значения String,
     * @param arg строковая перменная для обозначения нациаональности
     * @return возвращающий объект типа Country
     */
    public static Country parse(String arg) {
        if (arg == null) return null;
        else switch (arg) {
            case "RUSSIA":
                return RUSSIA;
            case "SPAIN":
                return  SPAIN;
            case "ITALY":
                return ITALY;
            default:
                return null;
        }
    }
}