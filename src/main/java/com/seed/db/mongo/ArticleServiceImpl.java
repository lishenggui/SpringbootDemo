package com.seed.db.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;


    @Override
    public int create(Article article) {
        Article save = articleRepository.save(article);
        return 1;
    }

    @Override
    public int delete(List<String> ids) {
        List<Article> deleteList = new ArrayList<>();

        for(String id:ids){
            Article article = new Article();
            article.setId(id);
            deleteList.add(article);
        }
        articleRepository.deleteAll(deleteList);

        return ids.size();
    }

    @Override
    public List<Article> list(String id) {
        return articleRepository.findByid(id);
    }

    @Override
    public List<Article> findByArticleName(String name) {
        return articleRepository.findByArticleNameLike(name);
    }
}
