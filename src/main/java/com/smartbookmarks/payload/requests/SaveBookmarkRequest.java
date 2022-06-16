package com.smartbookmarks.payload.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@Builder
@RequiredArgsConstructor
public class SaveBookmarkRequest {

    private final String id;
    private final String url;
    private final Set<String> keywords;
    private boolean visible;
}
