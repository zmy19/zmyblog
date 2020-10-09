package com.zmy.blog.entity;

import java.io.Serializable;

public class ArticleWithBLOBs extends Article implements Serializable {
    private String articleContent;

    private String articleSummary;

    private static final long serialVersionUID = 1L;

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent == null ? null : articleContent.trim();
    }

    public String getArticleSummary() {
        return articleSummary;
    }

    public void setArticleSummary(String articleSummary) {
        this.articleSummary = articleSummary == null ? null : articleSummary.trim();
    }
}