package kz.sandibekov.spring.securityjwt.repository;

import kz.sandibekov.spring.securityjwt.model.entity.mongo.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
}
