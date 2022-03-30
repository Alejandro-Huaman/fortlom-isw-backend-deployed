package com.example.fortlomisw.backend.api;


import com.example.fortlomisw.backend.domain.model.entity.PublicationComment;
import com.example.fortlomisw.backend.domain.service.PublicationCommentService;
import com.example.fortlomisw.backend.mapping.PublicationCommentMapper;
import com.example.fortlomisw.backend.resource.PublicationComment.PublicationCommentResource;
import com.example.fortlomisw.backend.resource.PublicationComment.CreatePublicationResource;
import com.example.fortlomisw.backend.resource.PublicationComment.UpdatePublicationComment;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class PublicationCommentController {


    @Autowired
    private PublicationCommentService commentService;

    @Autowired
    private PublicationCommentMapper mapper;

    @Autowired
    private ModelMapper mapping;

    @GetMapping("/comments")
    public Page<PublicationCommentResource> getAllComments(Pageable pageable) {
        return mapper.modelListToPage(commentService.getAll(), pageable);
    }

    @GetMapping("/comments/{commentId}")
    public PublicationCommentResource getCommentById(@PathVariable("commentId") Long commentId) {
        return mapper.toResource(commentService.getById(commentId));
    }

    @PostMapping("/users/{userId}/publications/{publicationId}/comments")
    public PublicationCommentResource createComment(@PathVariable Long userId, @PathVariable Long publicationId, @RequestBody CreatePublicationResource request) {
        PublicationComment comment = mapping.map(request, PublicationComment.class);
        return mapping.map(commentService.create(userId, publicationId, comment), PublicationCommentResource.class);
    }

    @GetMapping("/publications/{publicationId}/comments")
    public Page<PublicationCommentResource> getAllCommentsByPublicationId(@PathVariable Long publicationId,Pageable pageable) {
        return mapper.modelListToPage(commentService.getCommentByPublicationId(publicationId), pageable);
    }

    @PutMapping("/comments/{commentId}")
    public PublicationCommentResource updateComment(@PathVariable Long commentId, @RequestBody UpdatePublicationComment request) {
        return mapper.toResource(commentService.update(commentId, mapper.toModel(request)));
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {
        return commentService.delete(commentId);
    }

}