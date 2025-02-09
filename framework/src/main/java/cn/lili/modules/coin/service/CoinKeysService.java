package cn.lili.modules.coin.service;

import cn.lili.modules.coin.entity.CoinKeys;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 货币
 *
 * @author Chopper
 * @since 2020/11/17 3:44 下午
 */
public interface CoinKeysService extends IService<CoinKeys> {


    /**
     * 获得用户密钥
     *
     * @return
     */
    CoinKeys getMemberKeys(String id,String type);
}