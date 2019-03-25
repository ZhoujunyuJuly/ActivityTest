package com.example.doubanmovie.model.DetailMode;

/**
 * Created by zhoujunyu on 2019/3/21.
 */
public class RatingBean {
    /**
     * max : 10
     * average : 5.0
     * details : {"1":695,"3":1145,"2":1089,"5":161,"4":365}
     * stars : 25
     * min : 0
     */

    private int max;
    private Double average;
    private DetailsBean details;
    private String stars;
    private int min;

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public DetailsBean getDetails() {
        return details;
    }

    public void setDetails(DetailsBean details) {
        this.details = details;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

}
