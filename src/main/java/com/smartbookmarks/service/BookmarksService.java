package com.smartbookmarks.service;

import com.smartbookmarks.payload.requests.SaveBookmarkRequest;
import com.smartbookmarks.payload.requests.UpdateBookmarkRequest;
import com.smartbookmarks.payload.responses.DeleteBookmarkResponse;
import com.smartbookmarks.payload.responses.GetAllBookmarkResponse;
import com.smartbookmarks.payload.responses.GetBookmarkResponse;
import com.smartbookmarks.payload.responses.SaveBookmarkResponse;
import com.smartbookmarks.payload.responses.UpdateBookmarkResponse;
import org.springframework.http.ResponseEntity;

public interface BookmarksService {
    ResponseEntity<SaveBookmarkResponse> save(SaveBookmarkRequest request);
    ResponseEntity<GetAllBookmarkResponse> getAllByUser();
    ResponseEntity<GetBookmarkResponse> get(String id);
    ResponseEntity<UpdateBookmarkResponse> update(String id, UpdateBookmarkRequest request);
    ResponseEntity<DeleteBookmarkResponse> delete(String id);
}
