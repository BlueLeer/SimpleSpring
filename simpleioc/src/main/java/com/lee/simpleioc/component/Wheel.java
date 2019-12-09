package com.lee.simpleioc.component;

/**
 * @author WangLe
 * @date 2019/12/9 14:24
 * @description 轮胎类
 */
public class Wheel {
    /**
     * 轮胎品牌
     */
    private String brand;

    /**
     * 其他规格
     */
    private String specification;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    @Override
    public String toString() {
        return "Wheel{" +
                "brand='" + brand + '\'' +
                ", specification='" + specification + '\'' +
                '}';
    }
}
