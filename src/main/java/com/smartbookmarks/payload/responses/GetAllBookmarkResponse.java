package com.smartbookmarks.payload.responses;


import com.smartbookmarks.model.bookmark.Bookmark;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class GetAllBookmarkResponse {
   private Set<Bookmark> bookmarkSet;
}
