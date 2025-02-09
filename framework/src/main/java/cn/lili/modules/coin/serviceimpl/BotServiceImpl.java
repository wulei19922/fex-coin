package cn.lili.modules.coin.serviceimpl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.lili.modules.coin.entity.BinanceAccount;
import cn.lili.modules.coin.entity.CoinBot;
import cn.lili.modules.coin.entity.CoinKeys;
import cn.lili.modules.coin.mapper.CoinBotMapper;
import cn.lili.modules.coin.mapper.CoinKeysMapper;
import cn.lili.modules.coin.service.BinanceService;
import cn.lili.modules.coin.service.BotService;
import cn.lili.modules.coin.service.CoinKeysService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BotServiceImpl extends ServiceImpl<CoinBotMapper, CoinBot> implements BotService {


    @Autowired
    CoinBotMapper coinBotMapper;

    @Override
    public boolean startQBot(String id,boolean status) {
        QueryWrapper<CoinBot> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("member_id",id);
        queryWrapper.eq("type","BINANCE");
        List<CoinBot> coinBots = coinBotMapper.selectList(queryWrapper);
        if (coinBots.isEmpty()){
            CoinBot coinBot=new CoinBot();
            coinBot.setMemberId(id);
            coinBot.setType("BINANCE");
            coinBot.setOpenStatus(status);
            this.save(coinBot);
        }else{
            CoinBot coinBot = coinBots.get(0);
            coinBot.setOpenStatus(status);
            this.saveOrUpdate(coinBot);
        }
        return true;
    }

    @Override
    public CoinBot getBotInfo(String id) {

        QueryWrapper<CoinBot> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("member_id",id);
        queryWrapper.eq("type","BINANCE");
        List<CoinBot> coinBots = coinBotMapper.selectList(queryWrapper);

        if(!coinBots.isEmpty()){
            return coinBots.get(0);

        }else{
            return  null;
        }
    }
}
