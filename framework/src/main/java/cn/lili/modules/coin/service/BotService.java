package cn.lili.modules.coin.service;

import cn.lili.modules.coin.entity.BinanceAccount;
import cn.lili.modules.coin.entity.CoinBot;
import cn.lili.modules.coin.entity.CoinKeys;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface BotService   extends IService<CoinBot> {

    boolean startQBot(String id,boolean status);

    CoinBot getBotInfo(String id);

}
