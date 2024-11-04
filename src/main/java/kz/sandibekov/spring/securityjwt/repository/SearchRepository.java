package kz.sandibekov.spring.securityjwt.repository;

import kz.sandibekov.spring.securityjwt.model.entity.mongo.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SearchRepository {
    List<Post> findByText(String text);
}
