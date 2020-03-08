package Managers;

import java.io.IOException;

/**
 * Функциональный интерфейс, расширяемый абстрактным классом AbstractCommand
 * @author Нечкасова Олеся
 */
public interface Command {
    /**
     * Абстрактный метов, определяющий метод для описания действия выполняемой команды
     * @param args обозначает аргументы для введенной команды
     * @throws WrongAmountOfArgsException при неправильном введенном количестве аргументов
     * @throws IsEmptyException , если коллекция пуста
     * @throws IOException при ошибках парсинга
     */
    void execute(String... args) throws WrongAmountOfArgsException, IsEmptyException, IOException;
}
