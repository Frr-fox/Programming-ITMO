package Common;

import Common.StudyGroup.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.time.LocalDate;

/**
 * Класс, позволяющий работать с объектами коллекции
 * @author Нечкасова Олеся
 */
public class WorkWithObjects implements Serializable {
    private int id; //Значение этого поля должно генерироваться автоматически, Значение этого поля должно быть уникальным, Значение поля должно быть больше 0
    private String name; //Строка не может быть пустой, Поле не может быть null
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Значение этого поля должно генерироваться автоматически, Поле не может быть null
    private Long studentsCount; //Значение поля должно быть больше 0, Поле не может быть null
    private FormOfEducation formOfEducation; //Поле не может быть null
    private Semester semesterEnum; //Поле может быть null
    private Person groupAdmin; //Поле может быть null
    private Float x; //Поле не может быть null
    private Integer y; //Поле не может быть null, Максимальное значение поля: 911
    private String namePerson; //Поле не может быть null, Строка не может быть пустой
    private Long height; //Значение поля должно быть больше 0, Поле может быть null
    private Color eyeColor; //Поле может быть null
    private Color hairColor; //Поле может быть null
    private Country nationality; //Поле не может быть null
    private Location location; //Поле может быть null
    private float z;
    private Double w; //Поле не может быть null
    private String nameLocation; //Поле не может быть null

    /**
     * Метод для добавления элемента в коллекцию
     * @param scanner объект типа Scanner для ввода данных с клавиатуры
     * @return объект типа Client.Common.Client.Commands.Commands.StudyGroup
     */
    public StudyGroup addStudyGroup(BufferedReader scanner, Writer writer) throws IOException {
        setName(scanner, writer);
        setCoordinates(scanner, writer);
        setStudentsCount(scanner, writer);
        setFormOfEducation(scanner, writer);
        setSemesterEnum(scanner, writer);
        setPerson(scanner, writer);
        creationDate = LocalDate.now();
        return new StudyGroup(name, String.valueOf(creationDate), coordinates, studentsCount, formOfEducation, semesterEnum, groupAdmin);
    }

    /**
     * Метод для обновления значения элемента коллекции
     * @param studyGroup объект типа Client.Common.Client.Commands.Commands.StudyGroup для обновления
     * @param scanner объект типа Scanner для ввода данных с клавиатуры
     * @return объект типа Client.Common.Client.Commands.Commands.StudyGroup
     */
    public StudyGroup updateStudyGroup(StudyGroup studyGroup, BufferedReader scanner, Writer writer) throws IOException {
        setName(scanner, writer);
        setCoordinates(scanner, writer);
        setStudentsCount(scanner, writer);
        setFormOfEducation(scanner, writer);
        setSemesterEnum(scanner, writer);
        setPerson(scanner, writer);
        studyGroup.setName(name);
        studyGroup.setCoordinates(coordinates);
        studyGroup.setStudentsCount(studentsCount);
        studyGroup.setFormOfEducation(formOfEducation);
        studyGroup.setSemesterEnum(semesterEnum);
        studyGroup.setGroupAdmin(groupAdmin);
        return new StudyGroup(name, coordinates, studentsCount, formOfEducation, semesterEnum, groupAdmin);
    }

    /**
     * Метод для задания поля {@link WorkWithObjects#name}
     * @param scanner объект типа Scanner для ввода данных с клавиатуры
     */
    private void setName(BufferedReader scanner, Writer writer) throws IOException {
        try {
            writer.write("\nВведите название группы: ");
            writer.flush();
            String s = scanner.readLine();
            if ((s != null) && (!s.equals(""))) {
                name = s;
            } else {
                writer.write("\nВведите значение в нужном формате. ");
                setName(scanner, writer);
            }
            writer.flush();
        } catch (IOException e){
            writer.write("\nВозникла ошибка при считывании строки: " + e.getMessage());
            writer.flush();
        }
    }

    /**
     * Метод для задания поля {@link WorkWithObjects#coordinates}
     * @param scanner объект типа Scanner для ввода данных с клавиатуры
     */
    private void setCoordinates(BufferedReader scanner, Writer writer) throws IOException {
        writer.write("\nУкажите значения координат. ");
        writer.write("\nВведите координату x (от -3.4*10^38 до 3.4*10^38): ");
        writer.flush();
        try {
            String s = scanner.readLine();
            if ((s != null)) {
                try{
                    x = Float.parseFloat(s);
                }
                catch (NumberFormatException e ){
                    writer.write("\nВведите значение в нужном формате. ");
                    writer.flush();
                    setCoordinates(scanner, writer);
                }
            }
            writer.write("\nВведите координату y (от -2_147_483_648 до 911): ");
            writer.flush();
            s = scanner.readLine();
            if ((s != null)) {
                try{
                    int i = Integer.parseInt(s);
                    if (i<=911){
                        y = i;
                    } else {
                        throw new NumberFormatException();
                    }
                }
                catch (NumberFormatException e ){
                    writer.write("\nВведите значение в нужном формате. ");
                    writer.flush();
                    setCoordinates(scanner, writer);
                }
            }
            coordinates = new Coordinates(x, y);
        } catch (IOException e){
            writer.write("\nВозникла ошибка при считывании строки: " + e.getMessage());
            writer.flush();
        }
    }

    /**
     * Метод для задания поля {@link WorkWithObjects#studentsCount}
     * @param scanner объект типа Scanner для ввода данных с клавиатуры
     */
    private void setStudentsCount(BufferedReader scanner, Writer writer) throws IOException {
        writer.write("\nВведите количество студентов в группе (от 0, не включая, до 9_223_372_036_854_775_807): ");
        writer.flush();
        try{
            String s = scanner.readLine();
            if (s != null) {
                try{
                    long l = Long.parseLong(s);
                    if (l>0){
                        studentsCount = l;
                    } else {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    writer.write("\nВведите значение в нужном формате. ");
                    writer.flush();
                    setStudentsCount(scanner, writer);
                }
            }
        } catch (IOException e){
            writer.write("\nВозникла ошибка при считывании строки: " + e.getMessage());
            writer.flush();
        }
    }

    /**
     * Метод для задания поля {@link WorkWithObjects#formOfEducation}
     * @param scanner объект типа Scanner для ввода данных с клавиатуры
     */
    private void setFormOfEducation(BufferedReader scanner, Writer writer) throws IOException {
        writer.write("\nВведите форму обучения (Дистанционное, Полное, Вечернее): ");
        writer.flush();
        try {
            String s = scanner.readLine().toLowerCase();
            switch (s) {
                case "дистанционное":
                    formOfEducation = FormOfEducation.DISTANCE_EDUCATION;
                    break;
                case "полное":
                    formOfEducation = FormOfEducation.FULL_TIME_EDUCATION;
                    break;
                case "вечернее":
                    formOfEducation = FormOfEducation.EVENING_CLASSES;
                    break;
                default:
                    writer.write("\nВведите значение в нужном формате. ");
                    writer.flush();
                    setFormOfEducation(scanner, writer);
            }
        } catch (IOException e){
            writer.write("\nВозникла ошибка при считывании строки: " + e.getMessage());
            writer.flush();
        }
    }

    /**
     * Метод для задания поля {@link WorkWithObjects#semesterEnum}
     * @param scanner объект типа Scanner для ввода данных с клавиатуры
     */
    private void setSemesterEnum(BufferedReader scanner, Writer writer) throws IOException {
        writer.write("\nВведите номер семестра (Первый, Третий, Четвертый, Пятый, Седьмой): ");
        writer.flush();
        try {
            String s = scanner.readLine().toLowerCase();
            switch (s) {
                case "первый":
                    semesterEnum = Semester.FIRST;
                    break;
                case "третий":
                    semesterEnum = Semester.THIRD;
                    break;
                case "четвертый":
                    semesterEnum = Semester.FOURTH;
                    break;
                case "пятый":
                    semesterEnum = Semester.FIFTH;
                    break;
                case "седьмой":
                    semesterEnum = Semester.SEVENTH;
                    break;
                case "":
                    semesterEnum = null;
                    break;
                default:
                    writer.write("\nВведите значение в нужном формате. ");
                    writer.flush();
                    setSemesterEnum(scanner, writer);
            }
        } catch (IOException e){
            writer.write("\nВозникла ошибка при считывании строки: " + e.getMessage());
            writer.flush();
        }
    }

    /**
     * Метод для задания поля {@link WorkWithObjects#namePerson}
     * @param scanner объект типа Scanner для ввода данных с клавиатуры
     */
    private void setNamePerson(BufferedReader scanner, Writer writer) throws IOException {
        writer.write("\nВведите имя админа группы: ");
        writer.flush();
        try {
            String s = scanner.readLine();
            if ((s != null) && (!s.equals(""))) {
                namePerson = s;
            } else {
                writer.write("\nВведите значение в нужном формате. ");
                setNamePerson(scanner, writer);
            }
            writer.flush();
        } catch (IOException e) {
            writer.write("\nВозникла ошибка при считывании строки: " + e.getMessage());
            writer.flush();
        }
    }

    /**
     * Метод для задания поля {@link WorkWithObjects#height}
     * @param scanner объект типа Scanner для ввода данных с клавиатуры
     */
    private void setHeight(BufferedReader scanner, Writer writer) throws IOException {
        writer.write("\nВведите рост админа группы (от 0, не включая, до 9_223_372_036_854_775_807): ");
        writer.flush();
        try {
            String s = scanner.readLine();
            if (s != null) {
                try{
                    long l = Long.parseLong(s);
                    if (l>0){
                        height = l;
                    } else {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    writer.write("\nВведите значение в нужном формате. ");
                    setHeight(scanner, writer);
                }
            }
            writer.flush();
        } catch (IOException e) {
            writer.write("\nВозникла ошибка при считывании строки: " + e.getMessage());
            writer.flush();
        }
    }

    /**
     * Метод для задания поля {@link WorkWithObjects#eyeColor}
     * @param scanner объект типа Scanner для ввода данных с клавиатуры
     */
    private void setEyeColor(BufferedReader scanner, Writer writer) throws IOException {
        writer.write("\nВведите цвет глаз (Черный, Синий, Желтый): ");
        writer.flush();
        try {
            String s = scanner.readLine().toLowerCase();
            switch (s) {
                case "черный":
                    eyeColor = Color.BLACK;
                    break;
                case "синий":
                    eyeColor = Color.BLUE;
                    break;
                case "желтый":
                    eyeColor = Color.YELLOW;
                    break;
                case "":
                    eyeColor = null;
                    break;
                default:
                    writer.write("\nВведите значение в нужном формате. ");
                    writer.flush();
                    setEyeColor(scanner, writer);
            }
        } catch (IOException e) {
            writer.write("\nВозникла ошибка при считывании строки: " + e.getMessage());
            writer.flush();
        }
    }

    /**
     * Метод для задания поля {@link WorkWithObjects#hairColor}
     * @param scanner объект типа Scanner для ввода данных с клавиатуры
     */
    private void setHairColor(BufferedReader scanner, Writer writer) throws IOException {
        writer.write("\nВведите цвет волос (Черный, Синий, Желтый): ");
        writer.flush();
        try {
            String s = scanner.readLine().toLowerCase();
            switch (s) {
                case "черный":
                    hairColor = Color.BLACK;
                    break;
                case "синий":
                    hairColor = Color.BLUE;
                    break;
                case "желтый":
                    hairColor = Color.YELLOW;
                    break;
                case "":
                    hairColor = null;
                    break;
                default:
                    writer.write("\nВведите значение в нужном формате. ");
                    writer.flush();
                    setHairColor(scanner, writer);
            }
        } catch (IOException e) {
            writer.write("\nВозникла ошибка при считывании строки: " + e.getMessage());
            writer.flush();
        }
    }

    /**
     * Метод для задания поля {@link WorkWithObjects#nationality}
     * @param scanner объект типа Scanner для ввода данных с клавиатуры
     */
    private void setNationality(BufferedReader scanner, Writer writer) throws IOException {
        writer.write("\nВведите национальность (Русский, Испанец, Итальянец): ");
        writer.flush();
        try {
            String s = scanner.readLine().toLowerCase();
            switch (s) {
                case "русский":
                    nationality = Country.RUSSIA;
                    break;
                case "испанец":
                    nationality = Country.SPAIN;
                    break;
                case "итальянец":
                    nationality = Country.ITALY;
                    break;
                default:
                    writer.write("\nВведите значение в нужном формате. ");
                    writer.flush();
                    setNationality(scanner, writer);
            }
        } catch (IOException e) {
            writer.write("\nВозникла ошибка при считывании строки: " + e.getMessage());
            writer.flush();
        }
    }

    /**
     * Метод для задания поля {@link WorkWithObjects#groupAdmin}
     * @param scanner объект типа Scanner для ввода данных с клавиатуры
     */
    private void setPerson(BufferedReader scanner, Writer writer) throws IOException {
        writer.write("\nЕсть ли у группы админ? (да/нет)\n");
        writer.flush();
        try {
            String s = scanner.readLine().toLowerCase();
            switch (s) {
                case "да":
                    writer.write("\nВведите данные админа группы. ");
                    writer.flush();
                    setNamePerson(scanner, writer);
                    setHeight(scanner, writer);
                    setEyeColor(scanner, writer);
                    setHairColor(scanner, writer);
                    setNationality(scanner, writer);
                    setLocation(scanner, writer);
                    break;
                case "нет":
                    groupAdmin = null;
                    break;
                default:
                    writer.write("\nВведите значение в нужном формате. ");
                    writer.flush();
                    setPerson(scanner, writer);
            }
        } catch (IOException e) {
            writer.write("\nВозникла ошибка при считывании строки: " + e.getMessage());
            writer.flush();
        }
    }

    /**
     * Метод для задания поля {@link WorkWithObjects#location}
     * @param scanner объект типа Scanner для ввода данных с клавиатуры
     */
    private void setLocation(BufferedReader scanner, Writer writer) throws IOException {
        writer.write("\nЕсть ли у админа место? (Да/Нет)\n");
        writer.flush();
        try {
            String s = scanner.readLine();
            switch (s) {
                case "да":
                    writer.write("\nВведите координату x (от -3.4*10^38 до 3.4*10^38)");
                    writer.flush();
                    s = scanner.readLine();
                    try {
                        z = Float.parseFloat(s);
                    } catch (NumberFormatException e) {
                        writer.write("\nВведите значение в нужном формате. ");
                        writer.flush();
                        setLocation(scanner, writer);
                    }
                    writer.write("\nВведите координату у (от ±4.9*10^-324 до ±1.8*10^308)\n");
                    writer.flush();
                    s = scanner.readLine();
                    if (s != null) {
                        try {
                            w = Double.parseDouble(s);
                        } catch (NumberFormatException e) {
                            writer.write("\nВведите значение в нужном формате. ");
                            writer.flush();
                            setLocation(scanner, writer);
                        }
                    }
                    writer.write("\nВведите название места\n");
                    writer.flush();
                    s = scanner.readLine();
                    if (s != null) {
                        nameLocation = s;
                    } else {
                        writer.write("\nВведите значение в нужном формате. ");
                        writer.flush();
                        setLocation(scanner, writer);
                    }
                    location = new Location(z, w, nameLocation);
                    break;
                case "нет":
                    location = null;
                    break;
                default:
                    writer.write("\nВведите значение в нужном формате. ");
                    writer.flush();
                    setLocation(scanner, writer);
            }
            groupAdmin = new Person(namePerson, height, eyeColor, hairColor, nationality, location);
        } catch (IOException e) {
            writer.write("\nВозникла ошибка при считывании строки: " + e.getMessage());
            writer.flush();
        }
    }

}
