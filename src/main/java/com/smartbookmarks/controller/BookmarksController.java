package com.smartbookmarks.controller;


import com.smartbookmarks.payload.requests.SaveBookmarkRequest;
import com.smartbookmarks.payload.requests.UpdateBookmarkRequest;
import com.smartbookmarks.payload.responses.DeleteBookmarkResponse;
import com.smartbookmarks.payload.responses.GetAllBookmarkResponse;
import com.smartbookmarks.payload.responses.GetBookmarkResponse;
import com.smartbookmarks.payload.responses.SaveBookmarkResponse;
import com.smartbookmarks.payload.responses.UpdateBookmarkResponse;
import com.smartbookmarks.service.BookmarksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/bookmark")
@RequiredArgsConstructor
public class BookmarksController {

    private final BookmarksService bookmarksService;

    @PostMapping
    public ResponseEntity<SaveBookmarkResponse> save(@RequestBody SaveBookmarkRequest request) {
        return bookmarksService.save(request);
    }

    @GetMapping
    public ResponseEntity<GetAllBookmarkResponse> getAllByUser() {
        return bookmarksService.getAllByUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetBookmarkResponse> get(@PathVariable String id) {
        return bookmarksService.get(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateBookmarkResponse> update(@PathVariable String id, @RequestBody UpdateBookmarkRequest request) {
        return bookmarksService.update(id,request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteBookmarkResponse> delete(@PathVariable String id) {
        return bookmarksService.delete(id);
    }
}
