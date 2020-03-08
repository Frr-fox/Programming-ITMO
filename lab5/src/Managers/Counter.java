package Managers;

import StudyGroup.StudyGroup;

import java.util.Map;
import java.util.Random;

import static Managers.CollectionManager.studyGroupMap;

/**
 * Класс для генерации уникального значения id
 * @author Нечкасова Олеся
 */
public class Counter {
    static int count = 0;

    /**
     * Метод, предназначенный для возвращения
     * @return сгенерируемого значения id
     */
    public static int increase(){
        count++;
        return count;
    }

    public static int generate(){
        boolean flag = false;
        while (!flag){
            count = (int) (Math.random()*1000000);
            for (Map.Entry<Integer, StudyGroup> entry: studyGroupMap.entrySet()){
                if (count != entry.getValue().getId()) {
                    flag = true;
                    break;
                }
            }
        }
        return count;
    }

    public static void clearing(){
        count = 0;
    }
}
