package cn.lili.modules.coin.service;

import cn.lili.modules.coin.entity.CoinKeys;
import cn.lili.modules.coin.entity.Version;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 货币
 *
 * @author Chopper
 * @since 2020/11/17 3:44 下午
 */
public interface VersionService extends IService<Version> {


    /**
     * 获得用户密钥
     *
     * @return
     */
    Version getVersion();
}