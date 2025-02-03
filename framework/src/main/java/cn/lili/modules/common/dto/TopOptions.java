package cn.lili.modules.common.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TopOptions {

    String baseCoin;
    String pricingCoin;
    Float open;
    Float close;
}
