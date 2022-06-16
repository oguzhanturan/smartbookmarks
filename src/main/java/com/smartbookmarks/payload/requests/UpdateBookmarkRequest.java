package com.smartbookmarks.payload.requests;

import com.smartbookmarks.model.bookmark.Bookmark;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateBookmarkRequest {
    private Bookmark bookmark;
}
