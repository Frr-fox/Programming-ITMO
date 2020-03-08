package StudyGroup;

/**
 * Перечисляемый тип для задания значений eyeColor и hairColor
 * у объектов типа StudyGroup
 * @author Нечкасова Олеся
 */

public enum Color {
    BLACK("Черный"),
    BLUE("Синий"),
    YELLOW("Желтый");

    /** Поле название константы**/
    private String title;

    /**
     * Конструктор для константных значений
     * @param title сроковая переменная, обохначающая цвет
     */
    Color(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    /**
     * Метод для парсинга переменной значения String,
     * @param arg сроковая переменная, обохначающая цвет
     * @return возвращающий объект типа Color
     */
    public static Color parse(String arg) {
        switch (arg) {
            case "BLACK":
                return BLACK;
            case "BLUE":
                return  BLUE;
            case "YELLOW":
                return YELLOW;
            default:
                return null;
        }
    }
}