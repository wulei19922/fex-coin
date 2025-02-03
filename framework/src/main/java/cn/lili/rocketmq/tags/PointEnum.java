package cn.lili.rocketmq.tags;

/**
 * 积分类型
 *
 * @author paulG
 * @since 2020/12/9
 **/
public enum PointEnum {
    /**
     * 会员注册
     */
    SHOP_POINT("商城积分"),
    POINT_A("A积分"),
    POINT_B("B积分"),
    POINT_C("C积分"),

    MEMBER_CONNECT_LOGIN("会员使用联合登录");

    private final String description;

    PointEnum(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }


}
