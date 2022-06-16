package com.smartbookmarks.payload.responses;

import com.smartbookmarks.model.bookmark.Bookmark;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetBookmarkResponse {
    private Bookmark bookmark;
}
