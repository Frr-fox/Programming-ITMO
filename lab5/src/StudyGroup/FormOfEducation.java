package StudyGroup;

/**
 * Перечисляемый тип для задания значений formOfEducation
 * у объектов типа StudyGroup
 * @author Нечкасова Олеся
 */

public enum FormOfEducation {
    DISTANCE_EDUCATION("Дистанционное"),
    FULL_TIME_EDUCATION("Полное"),
    EVENING_CLASSES("Вечернее");

    /** Поле название константы**/
    private String title;

    /**
     * Конструктор для константных значений
     * @param title строковая переменная для обозначения формы обучения
     */
    FormOfEducation(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    /**
     * Метод для парсинга переменной значения String,
     * @param arg строковая переменная для обозначения формы обучения
     * @return возвращающий объект типа FormOfEducation
     */
    public static FormOfEducation parse(String arg) {
        switch (arg) {
            case "DISTANCE_EDUCATION":
                return DISTANCE_EDUCATION;
            case "FULL_TIME_EDUCATION":
                return FULL_TIME_EDUCATION;
            case "EVENING_CLASSES":
                return EVENING_CLASSES;
            default:
                return null;
        }
    }
}