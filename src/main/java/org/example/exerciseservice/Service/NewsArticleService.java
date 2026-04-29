package org.example.exerciseservice.Service;

import org.example.exerciseservice.Model.NewsArticle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NewsArticleService {

    ArrayList<NewsArticle> newsArticles = new ArrayList<>();

    public ArrayList<NewsArticle> getNewsArticles(){
        return newsArticles;
    }

    public void addNewsArticles(NewsArticle newsArticle){
        newsArticles.add(newsArticle);
    }


    public boolean updateNewsArticle(NewsArticle newsArticle, String id){
        for (int i=0;i< newsArticles.size();i++){
            if (newsArticles.get(i).getId().equalsIgnoreCase(id)){
                newsArticles.set(i, newsArticle);
                return true;
            }
        }
        return false;
    }


    public boolean deleteNewsArticle(String id){
        for (int i=0;i< newsArticles.size();i++){
            if (newsArticles.get(i).getId().equalsIgnoreCase(id)){
                newsArticles.remove(i);
                return true;
            }
        }
        return false;
    }


    public int publisNewsArticle(String id){
        for (int i=0;i< newsArticles.size();i++) {
            if (newsArticles.get(i).getId().equalsIgnoreCase(id)) {
                if (!newsArticles.get(i).isPublished()){
                    newsArticles.get(i).setPublished(true);
                    return 0;
                }else return 1;
            }
        }
        return 2;
    }


    public ArrayList<NewsArticle> searchIsPublished(){
        ArrayList<NewsArticle> published = new ArrayList<>();
        for (int i=0;i< newsArticles.size();i++){
            if (newsArticles.get(i).isPublished()){
                published.add(newsArticles.get(i));
            }
        }
        return published;
    }


    public ArrayList<NewsArticle> searchByCategory(String category){
        ArrayList<NewsArticle> categorys = new ArrayList<>();
        for (int i=0;i< newsArticles.size();i++){
            if (newsArticles.get(i).getCategory().equalsIgnoreCase(category)){
                categorys.add(newsArticles.get(i));
            }
        }
        return categorys;
    }

}
