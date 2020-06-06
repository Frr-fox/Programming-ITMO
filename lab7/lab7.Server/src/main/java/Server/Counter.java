package Server;

import Common.StudyGroup.StudyGroup;

import java.util.Map;

/**
 * Класс для генерации уникального значения id
 * @author Нечкасова Олеся
 */
public class Counter {
    int count = 0;
    CollectionManager collectionManager;

    Counter(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, предназначенный для возвращения
     * @return сгенерируемого значения id
     */
    public int increase(){
        count++;
        return count;
    }

    public int generate(){
        boolean flag = false;
        while (!flag){
            count = (int) (Math.random()*1000000);
            for (Map.Entry<Integer, StudyGroup> entry: collectionManager.getStudyGroupMap().entrySet()){
                if (count != entry.getValue().getId()) {
                    flag = true;
                    break;
                }
            }
        }
        return count;
    }

    public void clearing(){
        count = 0;
    }
}
