package xyz.yuanwl.domain;

import lombok.Data;

/**
 * 订单。
 * Order实体类中属性名和`order`表中的字段名是不一致的：<br>
 * id===>order_id <br>
 * orderNo===>order_no <br>
 * price===>order_price <br>
 *
 * @author Yuanwl
 * @date 2018-11-15 16:46:57
 * @version v1.0.0
 */
@Data
public class Order {
    private int id;
    private String orderNo;
    private float price;
}