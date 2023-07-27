package emailapp;

import com.mongodb.client.*;
import org.bson.Document;

public class MongoDBManager {
    private final MongoClient mongoClient;
    private final String databaseName;
    private final String collectionName;

    public MongoDBManager(MongoClient mongoClient, String databaseName, String collectionName) {
        this.mongoClient = mongoClient;
        this.databaseName = databaseName;
        this.collectionName = collectionName;
    }

    public void insertFormData(FormData formData) {
        try (MongoClient mongoClient = this.mongoClient) {
            MongoDatabase database = mongoClient.getDatabase(databaseName);
            MongoCollection<Document> collection = database.getCollection(collectionName);

            Document formDataDoc = new Document();
            formDataDoc.append("firstName", formData.getFirstName());
            formDataDoc.append("lastName", formData.getLastName());
            formDataDoc.append("company", formData.getCompany());
            formDataDoc.append("department", formData.getDepartment());
            formDataDoc.append("phoneNumber", formData.getPhoneNumber());
            formDataDoc.append("altEmail", formData.getAltEmail());
            formDataDoc.append("newPassword", formData.getNewPassword());
            formDataDoc.append("mailID", formData.getMailID());
            formDataDoc.append("employeeID", formData.getUniqueID());

            // Insert the document into the collection
            collection.insertOne(formDataDoc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
