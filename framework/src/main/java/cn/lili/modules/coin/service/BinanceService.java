package cn.lili.modules.coin.service;

import cn.lili.modules.coin.entity.BinanceAccount;

import java.util.List;

public interface BinanceService {




    public List<BinanceAccount> getAccountInfo();


    boolean startQBot(String id);
}
