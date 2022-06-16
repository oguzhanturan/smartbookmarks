package com.smartbookmarks.model.bookmark;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Document("bookmark")
@Builder
@Data
public class Bookmark {
    @Id
    private String id;

    @NotNull(message = "url is not null!")
    @Field(name = "url")
    @Indexed(unique = true)
    private String url;

    @NotNull(message = "keywords is not null!")
    @Field(name = "keywords")
    private Set<Keyword> keywords;

    @Field(name = "visible")
    @NotNull(message =  "keyword visible is not null")
    private boolean visible;

    @Field(name = "userId")
    private String userId;
}
