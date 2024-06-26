package com.villive.Backend.controller;

import com.villive.Backend.domain.PostCategory;
import com.villive.Backend.domain.PostsLike;
import com.villive.Backend.dto.MsgResponseDto;
import com.villive.Backend.dto.PostsRequestDto;
import com.villive.Backend.dto.PostsResponseDto;
import com.villive.Backend.jwt.CustomUserDetails;
import com.villive.Backend.service.PostsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
@Tag(name = "게시판", description = "게시판 API")
public class PostsController {

    private final PostsService postsService;

    // 게시판 조회
    @Operation(summary = "게시판 전체 조회")
    @GetMapping("/")
    public ResponseEntity<List<PostsResponseDto>> getPostsList() {
        return ResponseEntity.ok(postsService.getPostsList());
    }

    // 카테고리별 게시글 조회
    @Operation(summary = "카테고리별 게시글 조회", description = "카테고리를 선택해주세요." + "[단체, 공동구매, 민원, 나눔, 동호회]")
    @GetMapping("category/{category}")
    public ResponseEntity<List<PostsResponseDto>> getPostsByCategory(@PathVariable("category") PostCategory postCategory){
        List<PostsResponseDto> posts = postsService.getPostsListByCategory(postCategory);
        return ResponseEntity.ok(posts);
    }

    // 게시글 선택 조회
    @Operation(summary = "게시글 선택 후 조회", description = "게시판 목록에서 게시글을 조회하는 API입니다." + "{id}에 게시글 번호를 주세요.")
    @GetMapping("/{id}")
    public ResponseEntity<PostsResponseDto> getPosts(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails customUserDetails){
        return ResponseEntity.ok(postsService.getPosts(id, customUserDetails.getMember()));
    }

    // 게시글 작성
    @Operation(summary = "게시글 작성", description = "게시글 작성하는 API입니다.")
    @PostMapping("/write")
    public ResponseEntity<PostsResponseDto> createPosts(@RequestBody PostsRequestDto postsRequestDto, @AuthenticationPrincipal CustomUserDetails customUserDetails){
        PostsResponseDto responseDto = postsService.createPosts(postsRequestDto, customUserDetails.getMember());
        return ResponseEntity.ok(responseDto);
    }
    
    // 게시글 수정
    @Operation(summary = "게시글 수정", description = "게시글 수정하는 API입니다." + " {id}에 게시글 번호를 주세요.")
    @PutMapping("/{id}")
    public ResponseEntity<PostsResponseDto> updatePosts(@PathVariable Long id, @RequestBody PostsRequestDto postsRequestDto, @AuthenticationPrincipal CustomUserDetails customUserDetails){
        return ResponseEntity.ok(postsService.updatePosts(id, postsRequestDto, customUserDetails.getMember()));
    }

    // 게시글 삭제
    @Operation(summary = "게시글 삭제", description = "게시글 삭제하는 API입니다." + " {id}에 게시글 번호를 주세요.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MsgResponseDto> deletePosts(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails customUserDetails){
        return ResponseEntity.ok(postsService.deletePosts(id, customUserDetails.getMember()));
    }


    // 게시글 좋아요
    @Operation(summary = "게시글 좋아요", description = "게시글에 좋아요 API입니다." + " {id}에 게시글 번호를 주세요.")
    @PostMapping("/like/{id}")
    public ResponseEntity<MsgResponseDto> savePostsLike(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails customUserDetails){
        return ResponseEntity.ok(postsService.savePostsLike(id, customUserDetails.getMember()));
    }


}
