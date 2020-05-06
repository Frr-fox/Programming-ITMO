package Server;

import Common.StudyGroup.StudyGroup;

import static Server.CollectionManager.studyGroupMap;

import Common.ParseException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.Map;

/**
 * Класс, предназначенный для парсинга объектов типа Client.Common.Client.Common.Commands.Commands.Common.Common.StudyGroup в файл формата xml и обратно
 * @author Нечкасова Олеся
 */
class JAXBWorker {

    /**
     * Метод для сохранения дынных в файл формата xml
     * @param studyGroupMap задает объект типа LinkedHashMap
     * @param filepath задает путь к файлу
     */
    public static void save(CollectionManager studyGroupMap, String filepath) {
        convertObjectToXml(studyGroupMap, filepath);
    }

    /**
     * Метод для парсинга их xml в объект
     * @param filepath задает путь к файлу
     * @throws IOException при ошибках загрузки файла
     */
    public static void load(String filepath) throws IOException {
        CollectionManager collectionManager = fromXmlToObject(filepath);
        try {
            for (Map.Entry<Integer, StudyGroup> entry: studyGroupMap.entrySet()){
                entry.getValue().setThisKey(entry.getKey());
                if (!entry.getValue().parse()){
                    throw new ParseException();
                }
            }
        } catch (ParseException e) {
            studyGroupMap.clear();
        }
    }

    /**
     * Метод для парсинга из объекта в xml
     * @param studyGroupMap задает объект типа LinkedHashMap
     * @param filePath задает путь к файлу
     */
    private static void convertObjectToXml(CollectionManager studyGroupMap, String filePath) {
        try {
            PrintWriter pr = new PrintWriter(new File(filePath));
            StringWriter stringWriter = new StringWriter();
            JAXBContext context = JAXBContext.newInstance(CollectionManager.class);
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // маршаллинг объекта в файл
            marshaller.marshal(studyGroupMap, stringWriter);
            pr.print(stringWriter);
            pr.close();
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для парсинга из xml в объект
     * @param filepath задает путь к файлу
     * @return объект типа CollectionManager
     * @throws IOException при ошибках парсинга
     */
    protected static CollectionManager fromXmlToObject(String filepath) throws IOException {
        BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
        String xmlText = "";
        String xmlLine = inputStreamReader.readLine();
        while (xmlLine != null) {
            xmlText = xmlText + xmlLine + "\n";
            xmlLine = inputStreamReader.readLine();
        }
        StringReader reader = new StringReader(xmlText);
        try {
            JAXBContext context = JAXBContext.newInstance(CollectionManager.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (CollectionManager) unmarshaller.unmarshal(reader);
        } catch (JAXBException e){
            e.printStackTrace();
        }
        return null;
    }
}
