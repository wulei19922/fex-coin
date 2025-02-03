package cn.lili.event.impl;

import cn.lili.event.QuotesEvent;
import cn.lili.modules.coin.entity.CoinQuotes;
import cn.lili.modules.coin.service.CoinQuotesService;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@Slf4j
public class QuotesExecute  implements QuotesEvent {


    @Autowired
    CoinQuotesService coinQuotesService;
    @Override
    public void syncQuotesData() {



        JsonArray btcPrice = getBtcPrice();

        for (int i = 0; i < btcPrice.size(); i++) {
            JsonElement jsonElement = btcPrice.get(i);
            JsonObject data = jsonElement.getAsJsonObject();
            CoinQuotes coinQuotes=new CoinQuotes();
            coinQuotes.setPrice(data.get("price").getAsFloat());
            coinQuotes.setSymbol(data.get("symbol").getAsString());
            coinQuotesService.save(coinQuotes);
        }




    }


    private static final String BINANCE_API_URL = "https://api.binance.com/api/v3/ticker/price";

    public JsonArray getBtcPrice() {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(BINANCE_API_URL, String.class);

        // 使用Gson解析JSON响应
        log.info(result);
        JsonArray asJsonArray = JsonParser.parseString(result).getAsJsonArray();



        return asJsonArray;
    }

}
