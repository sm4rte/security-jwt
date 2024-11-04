package kz.sandibekov.spring.securityjwt.model.entity.mongo;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "JobPost")
@Getter
@Setter
public class Post {
    private String profile;
    private String desc;
    private int exp;
    private String[] techs;

}
