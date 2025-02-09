package cn.lili.modules.coin.serviceimpl;


import cn.lili.modules.coin.entity.CoinKeys;
import cn.lili.modules.coin.mapper.CoinKeysMapper;
import cn.lili.modules.coin.service.CoinKeysService;
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
public class CoinKeysServiceServiceImpl extends ServiceImpl<CoinKeysMapper, CoinKeys> implements CoinKeysService {


    @Autowired
    CoinKeysMapper  coinKeysMapper;



    @Override
    public CoinKeys getMemberKeys(String id,String type) {
        //BTC ETH BNB XRP SOL
        QueryWrapper<CoinKeys> queryWrapper = new QueryWrapper<>();
        // 添加查询条件
        queryWrapper.eq("member_id",id); // name = ?
        queryWrapper.eq("type",type); // name = ?
        List<CoinKeys> coinKeys = coinKeysMapper.selectList(queryWrapper);
        if(coinKeys.isEmpty()){
            return  null;
        }else{
            return coinKeys.get(0);
        }


    }
}