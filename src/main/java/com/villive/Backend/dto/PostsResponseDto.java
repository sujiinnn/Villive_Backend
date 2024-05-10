package com.villive.Backend.dto;

import com.villive.Backend.domain.PostCategory;
import com.villive.Backend.domain.Posts;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String contents;
    private String writer;
    private String createDate, modifiedDate;
    private Boolean isAnonymous;
    private PostCategory category;

    // 생성자
    public PostsResponseDto(Posts posts){
        this.id = posts.getId();
        this.writer = posts.getWriter();
        this.title = posts.getTitle();
        this.contents = posts.getContents();
        this.createDate = posts.getCreatedDate();
        this.modifiedDate = posts.getModifiedDate();
        this.isAnonymous = posts.getIsAnonymous();
        this.category = posts.getCategory();

    }


}
