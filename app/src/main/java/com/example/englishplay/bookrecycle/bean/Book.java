package com.example.englishplay.bookrecycle.bean;

public class Book {
    private String title;//书名
    private String id;//书籍ID
    private String author;//作者名，大于一个
    private String pubdate;//第一次出版时间
    private String image_media;//书籍图片
    private String binding;//平装之类的
    private String pages;//页数
    private String publisher;//出版社
    private String price;//价格

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getImage_media() {
        return image_media;
    }

    public void setImage_media(String image_media) {
        this.image_media = image_media;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
