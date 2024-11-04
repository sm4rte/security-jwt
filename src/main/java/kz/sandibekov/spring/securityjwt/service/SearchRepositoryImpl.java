package kz.sandibekov.spring.securityjwt.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import kz.sandibekov.spring.securityjwt.model.entity.mongo.Post;
import kz.sandibekov.spring.securityjwt.repository.SearchRepository;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchRepositoryImpl implements SearchRepository {
    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter converter;

    @Override
    public List<Post> findByText(String text) {

        final List<Post> posts = new ArrayList<>();

        MongoDatabase database = client.getDatabase("test");
        MongoCollection<org.bson.Document> collection = database.getCollection("JobPost");

        // Используем $text оператор вместо $search
        Document textSearchQuery = new Document("$text", new Document("$search", text));

        // Сортируем по опыту и лимитируем результаты
        collection.find((Bson) textSearchQuery)
                .sort(new Document("exp", 1))
                .limit(5)
                .forEach(doc -> posts.add(converter.read(Post.class, doc)));
        System.out.println("Search Query: " + text);
        return posts;
    }
}
