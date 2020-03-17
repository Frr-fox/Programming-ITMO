package Commands;

import Managers.*;

/**
 * Класс для задания конкретной команды help при работе с шаблоном Command
 * @author Нечкасова Олеся
 */
public class Help extends AbstractCommand {
    Console console;

    /**
     * Конструктор для создания нового объекта
     * @param console объект, выполняющий роль receiver'a
     */
    public Help(Console console){
        this.setCommandName("help");
        this.setText("Эта команда выводит справку по доступным командам");
        this.setArguments("");
        this.console = console;
    }

    /**
     * Переопредлеенный из абстрактного класса AbstractCommand метод,
     * позволяющий перенести конечную реализацию команды в метод {@link Console#help()}
     * @param args задает аргументы команды
     */
    @Override
    public void execute(String... args) {
        try {
            if (args.length == 0) {
                console.help();
            } else {
                throw new WrongAmountOfArgsException();
            }
        } catch (WrongAmountOfArgsException e) {
            System.out.println(e.getMessage());
        }
    }
}
