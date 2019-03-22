package com.example.doubanmovie.model.DetailMode;

/**
 * Created by zhoujunyu on 2019/3/21.
 */
public class PhotosBean {

    /**
     * thumb : https://img3.doubanio.com/view/photo/m/public/p2550813096.jpg
     * image : https://img3.doubanio.com/view/photo/l/public/p2550813096.jpg
     * cover : https://img3.doubanio.com/view/photo/sqs/public/p2550813096.jpg
     * alt : https://movie.douban.com/photos/photo/2550813096/
     * id : 2550813096
     * icon : https://img3.doubanio.com/view/photo/s/public/p2550813096.jpg
     */

    private String thumb;
    private String image;
    private String cover;
    private String alt;
    private String id;
    private String icon;

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
