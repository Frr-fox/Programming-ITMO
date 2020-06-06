package Common;

import java.io.Serializable;

/**
 * Класс для генерации уникального значения id
 * @author Нечкасова Олеся
 */
public class Counter implements Serializable {
    static int count = 0;

    /**
     * Метод, предназначенный для возвращения
     * @return сгенерируемого значения id
     */
    public static int increase(){
        count++;
        return count;
    }

    public static void clearing(){
        count = 0;
    }
}
