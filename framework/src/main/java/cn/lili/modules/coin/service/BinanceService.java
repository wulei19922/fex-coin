package cn.lili.modules.coin.service;

import cn.lili.modules.coin.entity.BinanceAccount;
import cn.lili.modules.coin.entity.ChargeAddress;

import java.util.List;

public interface BinanceService {




    public List<BinanceAccount> getAccountInfo();


    boolean startQBot(String id);

    ChargeAddress getChargeAddress(String memberId);
}
