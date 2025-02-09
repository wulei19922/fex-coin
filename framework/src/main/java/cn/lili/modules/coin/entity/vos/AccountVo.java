package cn.lili.modules.coin.entity.vos;


import cn.lili.modules.coin.entity.BinanceAccount;
import lombok.Data;

import java.util.List;

@Data
public class AccountVo {

    boolean botStatus;

    List<BinanceAccount> list;


}
