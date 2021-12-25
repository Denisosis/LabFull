package dataProviders;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.example.HistoryContent;
import org.example.enums.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.example.ConstantsProperties;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class DataProviderTools {
    private static final Logger log = LogManager.getLogger(DataProviderTools.class);
    // CSV
    public static <T> boolean saveCSV(List<T> list, String csvDir) {
        boolean isSaved;
        try {
            CSVWriter csvWriter = new CSVWriter(new FileWriter(csvDir));
            StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(csvWriter).build();
            beanToCsv.write(list);
            csvWriter.close();
            isSaved = true;
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            log.error(e);
            isSaved = false;
        }
        return isSaved;
    }
    public static <T> Optional<List<T>> readCSV(Class<?> type, String csvDir) {
        Optional<List<T>> opList;
        File file = new File(csvDir);
        if(file.exists()) {
            try {
                FileReader reader = new FileReader(csvDir);
                opList = Optional.of(new CsvToBeanBuilder<T>(reader).withType((Class<? extends T>) type).build().parse());
            } catch (IOException e) {
                log.error(e);
                opList = Optional.empty();
            }
        } else {
            opList = Optional.empty();
        }
        return opList;
    }

    // MongoDB
    public static boolean saveHistoryContent(String className, Status status, Object json){
        boolean isSaved;
        try {
            MongoClient mongoClient = MongoClients.create(ConstantsProperties.MONGODB_HOST);
            MongoDatabase database = mongoClient.getDatabase(ConstantsProperties.MONGODB_DATABASE_NAME);
            try {
                database.createCollection(ConstantsProperties.MONGODB_COLLECTION_NAME);
            } catch (Exception e) {
                log.info(ConstantsProperties.MONGODB_COLLECTION_ALREADY_EXIST);
            }
            HistoryContent historyContent = new HistoryContent(className, status, new Gson().toJson(json));
            MongoCollection<Document> collection = database.getCollection(ConstantsProperties.MONGODB_COLLECTION_NAME);
            collection.insertOne(Document.parse(new Gson().toJson(historyContent)));
            isSaved = true;
        } catch (Exception e) {
            log.error(e);
            isSaved = false;
        }
        return isSaved;
    }

    // MySQL
}
