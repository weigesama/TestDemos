package xyz.yuanwl.domain;

import lombok.Data;

import java.util.List;

/**
 * 人
 * @author Yuanwl
 * @date 2018-11-16 12:36:54
 * @version v1.0.0
 */
@Data
public class Person {
    private int id;
    private String name;

    private IdCard idCard;
    private List<BankCard> bankCards;
}