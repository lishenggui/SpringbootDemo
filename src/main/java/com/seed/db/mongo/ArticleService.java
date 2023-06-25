package com.seed.db.mongo;

import java.util.List;


public interface ArticleService {

    int create(Article article);

    int  delete(List<String> ids);

    List<Article> list(String id);


    List<Article> findByArticleName(String  name);

}
