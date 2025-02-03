package cn.lili.modules.coin.serviceimpl;


import cn.lili.modules.coin.entity.CoinQuotes;
import cn.lili.modules.coin.mapper.CoinQuotesMapper;
import cn.lili.modules.coin.service.CoinQuotesService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会员接收消息业务层实现
 *
 * @author Chopper
 * @since 2020/11/17 3:48 下午
 */
@Service
public class CoinQuotesServiceServiceImpl extends ServiceImpl<CoinQuotesMapper, CoinQuotes> implements CoinQuotesService {


    @Autowired
    CoinQuotesMapper coinQuotesMapper;

    @Override
    public List<CoinQuotes> getToplist() {

        //BTC ETH BNB XRP SOL
        QueryWrapper<CoinQuotes> queryWrapper = new QueryWrapper<>();

        // 添加查询条件
        queryWrapper.in("symbol", "BTCUSDT","ETHUSDT","BNBUSDT","XRPUSDT","SOLUSDT"); // name = ?
        queryWrapper.orderByAsc("price"); // 按 age 升序排序
        List<CoinQuotes> coinQuotes = coinQuotesMapper.selectList(queryWrapper);


        return coinQuotes;
    }
}