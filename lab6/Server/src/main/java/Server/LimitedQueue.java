package Server;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Вспомогательный класс, для реализации команды history, реализующий такую структуру данных
 * как ограниченную очередь
 * @author Нечкасова Олеся
 * @param <E>
 */
public class LimitedQueue<E> extends LinkedList<E> {
    private int limit;

    /**
     * Конструктор для создания объекта типа LimitedQueue
     * @param limit обозначает предельное число в очереди
     */
    public LimitedQueue(int limit){
        this.limit = limit;
    }

    /**
     * Переопределенный метод для реализации ограниченной очереди
     * @param e обозначает объект параметра E
     * @return true, если элемент добавлен в очередь
     */
    @Override
    public boolean add(E e) {
        if (size()==limit+1) {
            removeFirst();
        }
        addLast(e);
        return true;
    }

    /**
     * Переопределенный метод, предназначенный для вывода результата
     * при использовании команды history
     * @return строку с последними 15-ю введенными командами
     */
    @Override
    public String toString(){
        E t;
        String s = "";
        Iterator<E> iter = this.iterator();
        while (iter.hasNext()) {
            t = iter.next();
            if (iter.hasNext()) {
                s += t  + ", ";
            }
        }
        if (!s.equals("")) {
            s = s.substring(0, s.length()-2);
        } else {
            s = "Нет ранее введенных команд. ";
        }
        return s;
    }
}
