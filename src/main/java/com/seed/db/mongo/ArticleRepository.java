package com.seed.db.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ArticleRepository extends MongoRepository<Article,String> {
    //根据id查询文章
    List<Article> findByid(String id);

    //根据id查询文章
    List<Article> findByArticleNameLike(String name);

}
