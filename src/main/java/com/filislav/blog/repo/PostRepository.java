package com.filislav.blog.repo;

import com.filislav.blog.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post,Long> { //<модель с которой работает репозиторий, тип уникального идентификатора>

}
