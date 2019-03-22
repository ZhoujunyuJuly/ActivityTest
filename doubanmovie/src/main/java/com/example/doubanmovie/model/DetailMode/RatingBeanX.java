package com.example.doubanmovie.model.DetailMode;

/**
 * Created by zhoujunyu on 2019/3/21.
 */
public class RatingBeanX {
    /**
     * max : 5
     * value : 1.0
     * min : 0
     */

    private int max;
    private Double value;
    private int min;

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
