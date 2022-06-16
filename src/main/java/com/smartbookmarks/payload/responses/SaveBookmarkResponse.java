package com.smartbookmarks.payload.responses;


import com.smartbookmarks.model.bookmark.Keyword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@RequiredArgsConstructor
public class SaveBookmarkResponse {
    private String id;
    private String url;
    private Set<Keyword> keywords;
    private boolean visible;
}
