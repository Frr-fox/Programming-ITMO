package Common.StudyGroup;

import java.io.Serializable;

/**
 * Перечисляемый тип для задания значений semesterEnum
 * у объектов типа Client.Common.Client.Commands.Commands.Common.StudyGroup
 * @author Нечкасова Олеся
 */

public enum Semester implements Serializable {
    FIRST("Первый"),
    THIRD("Третий"),
    FOURTH("Четвертый"),
    FIFTH("Пятый"),
    SEVENTH("Седьмой");

    /** Поле название константы**/
    private String title;

    /**
     * Конструктор для константных значений
     * @param title строковая переменная для обозначения номера семестра
     */
    Semester(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    /**
     * Метод для парсинга переменной значения String,
     * @param arg строковая переменная для обозначения номера семестра
     * @return возвращающий объект типа Semester
     */
    public static Semester parse(String arg) {
        switch (arg) {
            case "FIRST":
                return FIRST;
            case "THIRD":
                return THIRD;
            case "FOURTH":
                return FOURTH;
            case "FIFTH":
                return FIFTH;
            case "SEVENTH":
                return SEVENTH;
            default:
                return null;
        }
    }
}