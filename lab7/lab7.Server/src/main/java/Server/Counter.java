package Server;

import Common.StudyGroup.StudyGroup;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Класс для генерации уникального значения id
 * @author Нечкасова Олеся
 */
public class Counter {
    private AtomicInteger count = new AtomicInteger(0);
    CollectionManager collectionManager;

    Counter(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, предназначенный для возвращения
     * @return сгенерируемого значения id
     */
    public AtomicInteger increase(){
        count.incrementAndGet();
        return count;
    }

    public AtomicInteger generate(){
        boolean flag = false;
        while (!flag){
            count = new AtomicInteger((int)(Math.random()*1000000));
            for (Map.Entry<Integer, StudyGroup> entry: collectionManager.getStudyGroupMap().entrySet()){
                if (count != new AtomicInteger(entry.getValue().getId())) {
                    flag = true;
                    break;
                }
            }
        }
        return count;
    }

    public void clearing(){
        count = new AtomicInteger(0);
    }
}
