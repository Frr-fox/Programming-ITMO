package Common.StudyGroup;

import Common.ParseException;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Класс для описания учебной группы Client.Common.Client.Commands.Commands.Common.StudyGroup,
 * обладающий свойствами id, name, coordinates, creationDate, studentsCount, formOfEducation, semesterEnum, groupAdmin
 * @author Нечкасова Олеся
 */

@XmlRootElement(name = "Study")
@XmlType(propOrder = {"id", "name", "creation",  "coordinates", "studentsCount", "formOfEducation", "semesterEnum", "groupAdmin"} )
public class StudyGroup implements Serializable, Comparable <StudyGroup>{
    private int id; //Значение этого поля должно генерироваться автоматически, Значение этого поля должно быть уникальным, Значение поля должно быть больше 0
    private String name; //Строка не может быть пустой, Поле не может быть null
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Значение этого поля должно генерироваться автоматически, Поле не может быть null
    private Long studentsCount; //Значение поля должно быть больше 0, Поле не может быть null
    private FormOfEducation formOfEducation; //Поле не может быть null
    private Semester semesterEnum; //Поле может быть null
    private Person groupAdmin; //Поле может быть null

    private String creation;
    private int thisKey;

    public StudyGroup(){}

    /**
     *
     * @param name название группы
     * @param creation дата создания объекта группы
     * @param coordinates координаты
     * @param studentsCount количество студентов в группе
     * @param formOfEducation форма обучения
     * @param semesterEnum номер семестра
     * @param groupAdmin админ группы
     */
    public StudyGroup(String name, String creation, Coordinates coordinates, Long studentsCount, FormOfEducation formOfEducation, Semester semesterEnum, Person groupAdmin) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDate.parse(creation);
        this.creation = creation;
        this.studentsCount = studentsCount;
        this.formOfEducation = formOfEducation;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = groupAdmin;
    }

    /**
     * Конструктор для задания объекта Client.Common.Client.Commands.Commands.Common.StudyGroup
     * @param name название группы
     * @param x координата х
     * @param y координата y
     * @param studentsCount количество студентов в группе
     * @param formOfEducation форма обучения
     * @param semesterEnum номер семестра
     * @param namePerson имя админа группы
     * @param height рост админа группы
     * @param eyeColor цвет глаз админа группы
     * @param hairColor цвет волос админа группы
     * @param nationality национальность админа группы
     * @param z координата х местопоможения админа
     * @param w координата y местоположения админа
     * @param nameLocation название места, где находится админ
     */
    public StudyGroup(String name, Float x, Integer y, Long studentsCount, FormOfEducation formOfEducation, Semester semesterEnum, String namePerson, Long height, Color eyeColor, Color hairColor, Country nationality, Float z, Double w, String nameLocation){
        this.name = name;
        this.coordinates = new Coordinates(x, y);
        this.creationDate = LocalDate.now();
        this.studentsCount = studentsCount;
        this.formOfEducation = formOfEducation;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = new Person(namePerson, height, eyeColor, hairColor, nationality, z, w, nameLocation );
    }

    /**
     * Конструктор для задания объекта Client.Common.Client.Commands.Commands.Common.StudyGroup
     * @param name название группы
     * @param coordinates координаты
     * @param studentsCount количество студентов в группе
     * @param formOfEducation форма обучения
     * @param semesterEnum номер семестра
     * @param groupAdmin админ группы
     */
    public StudyGroup(String name, Coordinates coordinates, Long studentsCount, FormOfEducation formOfEducation, Semester semesterEnum, Person groupAdmin) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDate.now();
        this.studentsCount = studentsCount;
        this.formOfEducation = formOfEducation;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = groupAdmin;
    }

    public boolean parse(){
        if (name == null)
            return false;
        if (creation==null)
            return false;
        if (coordinates == null)
            return false;
        if (studentsCount == null){
            return false;
        } else {
            if (studentsCount <=0)
                return false;
        }
        return formOfEducation != null;
    }

    /**
     * Метод, возвращающий целочисленноезначение переменной
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Метод возвращающий название группы
     * @return name
     */
    public String getName() {
        return name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Метод, возвращающий объект типа
     * @return Coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Метод, возвращающий значение переменной
     * @return studentCount
     */
    public Long getStudentsCount() {
        return studentsCount;
    }

    /**
     * Метод, возвращающий значение перечисляемого типа
     * @return formOfEducation
     */
    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    /**
     * Метод, возвращающий значение перечисляемого типа
     * @return semesterEnum
     */
    public Semester getSemesterEnum() {
        return semesterEnum;
    }

    /**
     * Метод, возвращающий объект типа
     * @return Person
     */
    public Person getGroupAdmin() {
        return groupAdmin;
    }

    public void setNextId(int id) {
        this.id = id;
    }

    @XmlElement
    public void setId(int id) {
        this.id = id;
    }

    public void setCreationDate() {
        this.creationDate = LocalDate.now();
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getCreation() {
        return creation;
    }

    @XmlElement
    public void setCreation(String creation) {
        try {
            LocalDate.parse(creation);
            this.creation = creation;
        } catch (DateTimeException e){
            System.out.println("В одном или нескольких объектах введена неправильная дата. По умолчанию установлена текущая дата.");
            this.creation = LocalDate.now().toString();
        }
    }

    @XmlElement
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @XmlElement
    public void setStudentsCount(Long studentsCount) {
        this.studentsCount = studentsCount;
    }

    @XmlElement
    public void setFormOfEducation(FormOfEducation formOfEducation) {
        if (formOfEducation == null) {
            throw new ParseException();
        }
        this.formOfEducation = formOfEducation;
    }

    @XmlElement(nillable = true)
    public void setSemesterEnum(Semester semesterEnum) {
        this.semesterEnum = semesterEnum;
    }

    @XmlElement(nillable = true)
    public void setGroupAdmin(Person groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    public void setThisKey(int key) {
        this.thisKey = key;
    }

    @XmlTransient
    public int getThisKey() {
        return thisKey;
    }

    @Override
    public String toString() {
        if (groupAdmin == null) {
            return "id = " + id + "; name = " + name + "; coordinates: " + coordinates.toString()
                    + "; creationDate = " + creation + "; studentsCount = " + studentsCount + ";\nformOfEducation = " + formOfEducation
                    + "; semesterEnum = " + semesterEnum + "; Person: null";
        } else {
            return "id = " + id + "; name = " + name + "; coordinates: " + coordinates.toString()
                    + "; creationDate = " + creation + "; studentsCount = " + studentsCount + ";\nformOfEducation = " + formOfEducation
                    + "; semesterEnum = " + semesterEnum + ";\nPerson: " + groupAdmin.toString();
        }
    }

    @Override
    public int compareTo(StudyGroup o) {
//        return (int)(this.getStudentsCount()*sqrt(pow(this.getCoordinates().getX(), 2) + pow(this.getCoordinates().getY(), 2))-o.getStudentsCount()*sqrt(pow(o.getCoordinates().getX(), 2) + pow(o.getCoordinates().getY(), 2)));
        return (int)(this.getStudentsCount() - o.getStudentsCount());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == this.getClass()){
            return this.toString().equals(obj.toString());
        } else return false;
    }

    @Override
    public int hashCode() {
        return (int)(this.getStudentsCount()*sqrt(pow(this.getCoordinates().getX(), 2) + pow(this.getCoordinates().getY(), 2)));
    }
}
