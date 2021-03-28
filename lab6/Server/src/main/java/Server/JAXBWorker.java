package Server;

import Common.StudyGroup.StudyGroup;

import Common.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class JAXBWorker {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    /**
     * Метод для сохранения дынных в файл формата xml
     * @param studyGroupMap задает объект типа LinkedHashMap
     * @param filepath задает путь к файлу
     */
    public static void save(CollectionManager studyGroupMap, String filepath) {
        logger.info("Коллекция сохранена");
        convertObjectToXml(studyGroupMap, filepath);
    }

    /**
     * Метод для парсинга их xml в объект
     * @param filepath задает путь к файлу
     */
    public static CollectionManager load(String filepath) {
        logger.info("Выполняется загрузка коллекции...");
        CollectionManager collectionManager = null;
        try {
            collectionManager = fromXmlToObject(filepath);
            if (collectionManager != null) {
                for (Map.Entry<Integer, StudyGroup> entry : collectionManager.getStudyGroupMap().entrySet()) {
                    entry.getValue().setThisKey(entry.getKey());
                    if (!entry.getValue().parse()) {
                        throw new ParseException();
                    }
                }
                if (collectionManager.getStudyGroupMap().isEmpty()) {
                    logger.warn("Коллекция из файла не загружена. Неправильные данные в xml файле. ");
                } else {
                    logger.info("Коллекция из файла загружена!");
                }
            }
        } catch (ParseException e) {
            logger.warn("Возникла ошибка при парсинге данных");
            collectionManager.getStudyGroupMap().clear();
        } catch (IOException e){
            logger.warn("Возникла ошибка при загрузки файла");
        }
        return collectionManager;
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
