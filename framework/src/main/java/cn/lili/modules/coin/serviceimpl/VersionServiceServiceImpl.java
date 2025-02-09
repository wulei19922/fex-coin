package cn.lili.modules.coin.serviceimpl;


import cn.lili.modules.coin.entity.CoinKeys;
import cn.lili.modules.coin.entity.Version;
import cn.lili.modules.coin.mapper.CoinKeysMapper;
import cn.lili.modules.coin.mapper.VersionMapper;
import cn.lili.modules.coin.service.CoinKeysService;
import cn.lili.modules.coin.service.VersionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 查询版本信息
 *
 * @author Chopper
 * @since 2020/11/17 3:48 下午
 */
@Service
public class VersionServiceServiceImpl extends ServiceImpl<VersionMapper, Version> implements VersionService {

    @Autowired
    VersionMapper versionMapper;


    @Override
    public Version getVersion() {

        QueryWrapper<Version>queryWrapper=new QueryWrapper<>();
        List<Version> versions = versionMapper.selectList(queryWrapper);


        return versions.get(0);
    }
}