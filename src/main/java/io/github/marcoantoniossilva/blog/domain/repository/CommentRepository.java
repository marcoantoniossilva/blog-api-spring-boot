package io.github.marcoantoniossilva.blog.domain.repository;

import io.github.marcoantoniossilva.blog.domain.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {


  List<Comment> findAllByPostId(Long postId);

}
