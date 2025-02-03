package cn.lili.modules.coin.service;

import cn.lili.modules.coin.entity.CoinQuotes;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 会员消息发送业务层
 *
 * @author Chopper
 * @since 2020/11/17 3:44 下午
 */
public interface CoinQuotesService extends IService<CoinQuotes> {


    /**
     * 获得置顶货币信息
     */

    public  List<CoinQuotes>getToplist();





}