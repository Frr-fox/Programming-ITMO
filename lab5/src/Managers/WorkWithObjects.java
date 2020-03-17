package Managers;

import StudyGroup.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Класс, позволяющий работать с объектами коллекции
 * @author Нечкасова Олеся
 */
public class WorkWithObjects {
    private int id; //Значение этого поля должно генерироваться автоматически, Значение этого поля должно быть уникальным, Значение поля должно быть больше 0
    private String name; //Строка не может быть пустой, Поле не может быть null
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Значение этого поля должно генерироваться автоматически, Поле не может быть null
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
     * @return объект типа StudyGroup
     * @throws IOException если введены неправильные данные
     */
    public StudyGroup addStudyGroup(BufferedReader scanner) throws IOException {
        setName(scanner);
        setCoordinates(scanner);
        setStudentsCount(scanner);
        setFormOfEducation(scanner);
        setSemesterEnum(scanner);
        setPerson(scanner);
        creationDate = LocalDate.now();
        return new StudyGroup(name, String.valueOf(creationDate), coordinates, studentsCount, formOfEducation, semesterEnum, groupAdmin);
    }

    /**
     * Метод для обновления значения элемента коллекции
     * @param studyGroup объект типа StudyGroup для обновления
     * @param scanner объект типа Scanner для ввода данных с клавиатуры
     * @return объект типа StudyGroup
     * @throws IOException если введены неправильные данные
     */
    public StudyGroup updateStudyGroup(StudyGroup studyGroup, BufferedReader scanner) throws IOException {
        setName(scanner);
        setCoordinates(scanner);
        setStudentsCount(scanner);
        setFormOfEducation(scanner);
        setSemesterEnum(scanner);
        setPerson(scanner);
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
     * @throws IOException если введены неправильные данные
     */
    public void setName(BufferedReader scanner) throws IOException {
        System.out.print("Введите название группы: ");
        String s = scanner.readLine();
        if ((s != null) && (!s.equals(""))) {
            name = s;
        } else {
            System.out.print("Введите значение в нужном формате. ");
            setName(scanner);
        }
    }

    /**
     * Метод для задания поля {@link WorkWithObjects#coordinates}
     * @param scanner объект типа Scanner для ввода данных с клавиатуры
     * @throws IOException если введены неправильные данные
     */
    public void setCoordinates(BufferedReader scanner) throws IOException {
        System.out.println("Укажите значения координат.");
        System.out.print("Введите координату x (от -3.4*10^38 до 3.4*10^38): ");
        String s = scanner.readLine();
        if ((s != null)) {
            try{
                x = Float.parseFloat(s);
            }
            catch (NumberFormatException e ){
                System.out.print("Введите значение в нужном формате. ");
                setCoordinates(scanner);
            }
        }
        System.out.print("Введите координату y (от -2_147_483_648 до 911): ");
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
                System.out.print("Введите значение в нужном формате. ");
                setCoordinates(scanner);
            }
        }
        coordinates = new Coordinates(x, y);
    }

    /**
     * Метод для задания поля {@link WorkWithObjects#studentsCount}
     * @param scanner объект типа Scanner для ввода данных с клавиатуры
     * @throws IOException если введены неправильные данные
     */
    public void setStudentsCount(BufferedReader scanner) throws IOException {
        System.out.print("Введите количество студентов в группе (от 0, не включая, до 9_223_372_036_854_775_807): ");
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
                System.out.print("Введите значение в нужном формате. ");
                setStudentsCount(scanner);
            }
        }
    }

    /**
     * Метод для задания поля {@link WorkWithObjects#formOfEducation}
     * @param scanner объект типа Scanner для ввода данных с клавиатуры
     * @throws IOException если введены неправильные данные
     */
    public void setFormOfEducation(BufferedReader scanner) throws IOException {
        System.out.print("Введите форму обучения (Дистанционное, Полное, Вечернее): ");
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
                System.out.print("Введите значение в нужном формате. ");
                setFormOfEducation(scanner);
        }
    }

    /**
     * Метод для задания поля {@link WorkWithObjects#semesterEnum}
     * @param scanner объект типа Scanner для ввода данных с клавиатуры
     * @throws IOException если введены неправильные данные
     */
    public void setSemesterEnum(BufferedReader scanner) throws IOException {
        System.out.print("Введите номер семестра (Первый, Третий, Четвертый, Пятый, Седьмой): ");
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
                    System.out.print("Введите значение в нужном формате. ");
                    setSemesterEnum(scanner);
            }
    }

    /**
     * Метод для задания поля {@link WorkWithObjects#namePerson}
     * @param scanner объект типа Scanner для ввода данных с клавиатуры
     * @throws IOException если введены неправильные данные
     */
    public void setNamePerson(BufferedReader scanner) throws IOException {
        System.out.print("Введите имя админа группы: ");
        String s = scanner.readLine();
        if ((s != null) && (!s.equals(""))) {
            namePerson = s;
        } else {
            System.out.print("Введите значение в нужном формате. ");
            setNamePerson(scanner);
        }
    }

    /**
     * Метод для задания поля {@link WorkWithObjects#height}
     * @param scanner объект типа Scanner для ввода данных с клавиатуры
     * @throws IOException если введены неправильные данные
     */
    public void setHeight(BufferedReader scanner) throws IOException {
        System.out.print("Введите рост админа группы (от 0, не включая, до 9_223_372_036_854_775_807): ");
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
                System.out.print("Введите значение в нужном формате. ");
                setHeight(scanner);
            }
        }
    }

    /**
     * Метод для задания поля {@link WorkWithObjects#eyeColor}
     * @param scanner объект типа Scanner для ввода данных с клавиатуры
     * @throws IOException если введены неправильные данные
     */
    public void setEyeColor(BufferedReader scanner) throws IOException {
        System.out.print("Введите цвет глаз (Черный, Синий, Желтый): ");
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
                System.out.print("Введите значение в нужном формате. ");
                setEyeColor(scanner);
        }
    }

    /**
     * Метод для задания поля {@link WorkWithObjects#hairColor}
     * @param scanner объект типа Scanner для ввода данных с клавиатуры
     * @throws IOException если введены неправильные данные
     */
    public void setHairColor(BufferedReader scanner) throws IOException {
        System.out.print("Введите цвет волос (Черный, Синий, Желтый): ");
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
                System.out.print("Введите значение в нужном формате. ");
                setHairColor(scanner);
        }
    }

    /**
     * Метод для задания поля {@link WorkWithObjects#nationality}
     * @param scanner объект типа Scanner для ввода данных с клавиатуры
     * @throws IOException если введены неправильные данные
     */
    public void setNationality(BufferedReader scanner) throws IOException {
        System.out.print("Введите национальность (Русский, Испанец, Итальянец): ");
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
                System.out.print("Введите значение в нужном формате. ");
                setNationality(scanner);
        }
    }

    /**
     * Метод для задания поля {@link WorkWithObjects#groupAdmin}
     * @param scanner объект типа Scanner для ввода данных с клавиатуры
     * @throws IOException если введены неправильные данные
     */
    public void setPerson(BufferedReader scanner) throws IOException {
        System.out.println("Есть ли у группы админ? (да/нет)");
        String s = scanner.readLine().toLowerCase();
        switch (s) {
            case "да":
                System.out.print("Введите данные админа группы. ");
                setNamePerson(scanner);
                setHeight(scanner);
                setEyeColor(scanner);
                setHairColor(scanner);
                setNationality(scanner);
                setLocation(scanner);
                break;
            case "нет":
                groupAdmin = null;
                break;
            default:
                System.out.print("Введите значение в нужном формате. ");
                setPerson(scanner);
        }
    }

    /**
     * Метод для задания поля {@link WorkWithObjects#location}
     * @param scanner объект типа Scanner для ввода данных с клавиатуры
     * @throws IOException если введены неправильные данные
     */
    public void setLocation(BufferedReader scanner) throws IOException {
        System.out.println("Есть ли у админа место? (Да/Нет)");
        String s = scanner.readLine();
        switch (s) {
            case "да":
                System.out.println("Введите координату x (от -3.4*10^38 до 3.4*10^38)");
                s = scanner.readLine();
                try {
                    z = Float.parseFloat(s);
                } catch (NumberFormatException e) {
                    System.out.print("Введите значение в нужном формате. ");
                    setLocation(scanner);
                }
                System.out.println("Введите координату у (от ±4.9*10^-324 до ±1.8*10^308)");
                s = scanner.readLine();
                if (s != null) {
                    try {
                        w = Double.parseDouble(s);
                    } catch (NumberFormatException e) {
                        System.out.print("Введите значение в нужном формате. ");
                        setLocation(scanner);
                    }
                }
                System.out.println("Введите название места");
                s = scanner.readLine();
                if (s != null) {
                    nameLocation = s;
                } else {
                    System.out.print("Введите значение в нужном формате. ");
                    setLocation(scanner);
                }
                location = new Location(z, w, nameLocation);
                break;
            case "нет":
                location = null;
                break;
            default:
                System.out.print("Введите значение в нужном формате. ");
                setLocation(scanner);
        }
        groupAdmin = new Person(namePerson, height, eyeColor, hairColor, nationality, location);
    }
}
