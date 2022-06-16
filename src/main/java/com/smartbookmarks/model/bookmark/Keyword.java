package com.smartbookmarks.model.bookmark;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("keyword")
@Data
public class Keyword {
    @Id
    private String keyword;

    public Keyword(String keyword) {
        this.keyword = keyword;
    }

}
