package com.smartbookmarks.service;

import com.smartbookmarks.model.bookmark.Bookmark;
import com.smartbookmarks.model.bookmark.Keyword;
import com.smartbookmarks.payload.requests.SaveBookmarkRequest;
import com.smartbookmarks.payload.requests.UpdateBookmarkRequest;
import com.smartbookmarks.payload.responses.DeleteBookmarkResponse;
import com.smartbookmarks.payload.responses.GetAllBookmarkResponse;
import com.smartbookmarks.payload.responses.GetBookmarkResponse;
import com.smartbookmarks.payload.responses.SaveBookmarkResponse;
import com.smartbookmarks.payload.responses.UpdateBookmarkResponse;
import com.smartbookmarks.repository.BookmarksRepository;
import com.smartbookmarks.util.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookmarkServiceImpl implements BookmarksService {

    private final BookmarksRepository bookmarksRepository;

    @Override
    public ResponseEntity<SaveBookmarkResponse> save(SaveBookmarkRequest request) {

        Set<Keyword> collect = request.getKeywords().stream().map(Keyword::new).collect(Collectors.toSet());
        Bookmark bookmark = Bookmark.builder()
                .url(request.getUrl())
                .userId(AuthenticationUtil.getUserId())
                .keywords(collect)
                .visible(request.isVisible())
                .build();

        //ui dan gelen bookmark herzman eski + yeni olacak.
        SaveBookmarkResponse response = new SaveBookmarkResponse();
        bookmarksRepository.findByUrl(request.getUrl(), AuthenticationUtil.getUserId()).ifPresentOrElse((value) -> {
            value.setKeywords(collect);
            bookmarksRepository.save(value);
            response.setId(value.getId());
            response.setUrl(value.getUrl());
            response.setVisible(value.isVisible());
            response.setKeywords(value.getKeywords());
        }, () -> {
            Bookmark save = bookmarksRepository.save(bookmark);
            response.setId(save.getId());
            response.setUrl(save.getUrl());
            response.setVisible(save.isVisible());
            response.setKeywords(save.getKeywords());
        });

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<GetAllBookmarkResponse> getAllByUser() {

        GetAllBookmarkResponse response = new GetAllBookmarkResponse();
        bookmarksRepository.findAllByUserId(AuthenticationUtil.getUserId()).ifPresentOrElse(response::setBookmarkSet, () -> {
            throw new NotFoundException("bookmarks not found!");
        });
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<GetBookmarkResponse> get(String id) {
        GetBookmarkResponse response = new GetBookmarkResponse();
        bookmarksRepository.findByIdAndUserId(id, AuthenticationUtil.getUserId()).ifPresentOrElse(response::setBookmark, () -> {
            throw new NotFoundException("bookmark not found!");
        });
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<UpdateBookmarkResponse> update(String id, UpdateBookmarkRequest request) {
        bookmarksRepository.findByIdAndUserId(id, AuthenticationUtil.getUserId()).ifPresentOrElse(
                (value) -> {
                    value.setKeywords(request.getBookmark().getKeywords());
                    value.setUrl(request.getBookmark().getUrl());
                    value.setVisible(request.getBookmark().isVisible());
                    bookmarksRepository.save(value);
                }
                , () -> {
                    throw new NotFoundException("bookmark not found!");
                });
        return ResponseEntity.ok(new UpdateBookmarkResponse("success"));
    }

    @Override
    public ResponseEntity<DeleteBookmarkResponse> delete(String id) {
        bookmarksRepository.findByIdAndUserId(id, AuthenticationUtil.getUserId()).ifPresentOrElse(
                bookmarksRepository::delete
                , () -> {
                    throw new NotFoundException("bookmark not found!");
                });
        return ResponseEntity.ok(new DeleteBookmarkResponse("success"));
    }
}
