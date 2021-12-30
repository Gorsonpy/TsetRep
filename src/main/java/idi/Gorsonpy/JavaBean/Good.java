package idi.Gorsonpy.JavaBean;

import com.alibaba.fastjson.annotation.JSONField;

public class Good {
    @JSONField(name = "商品编号", ordinal = 1)
    private long id;
    //double默认只输出一位小数，用format控制输出两位小数符合实际
    @JSONField(name = "单品价格", format = "#.00", ordinal = 3)
    private double price;
    @JSONField(name = "商品名", ordinal = 2)
    private String name;
    @JSONField(name = "购买数量", ordinal = 4)
    private long account;

    /*
    设置一种全参数构造方法
    便于在GetHelper中的写入数据
     */
    public Good() {
        super();
    }

    public Good(long id, double price, String name, long account) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.account = account;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }


    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAccount(long account) {
        this.account = account;
    }

    public long getAccount() {
        return account;
    }
}
