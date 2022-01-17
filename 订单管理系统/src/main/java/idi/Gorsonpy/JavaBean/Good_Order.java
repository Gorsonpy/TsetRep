package idi.Gorsonpy.JavaBean;

import com.alibaba.fastjson.annotation.JSONField;

public class Good_Order {
    @JSONField(name = "记录编号", serialize = false)
    private long id;
    @JSONField(name = "订单编号", ordinal = 1)
    private long order_id;
    @JSONField(name = "商品编号", ordinal = 2)
    private long good_id;
    @JSONField(name = "购买数量", ordinal = 3)
    private long account;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public long getOrder_id() {
        return order_id;
    }

    public void setGood_id(long good_id) {
        this.good_id = good_id;
    }

    public long getGood_id() {
        return good_id;
    }

    public void setAccount(long account) {
        this.account = account;
    }

    public long getAccount() {
        return account;
    }
}
