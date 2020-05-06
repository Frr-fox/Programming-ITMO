package Common;

import java.io.Serializable;

/**
 * Функциональный интерфейс, расширяемый абстрактным классом AbstractCommand
 * @author Нечкасова Олеся
 */
public interface Command extends Serializable {
    /**
     * Абстрактный метод, определяющий реализацию выполняемой команды
     * @param args аргументы команды
     */
    void execute(Object[] args);
}
