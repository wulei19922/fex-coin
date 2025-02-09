package cn.lili.modules.coin.serviceimpl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.lili.modules.coin.entity.BinanceAccount;
import cn.lili.modules.coin.entity.CoinBot;
import cn.lili.modules.coin.mapper.CoinBotMapper;
import cn.lili.modules.coin.service.BinanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@Slf4j
public class BinanceServiceImpl  implements BinanceService {


    @Autowired
    CoinBotMapper coinBotMapper;

    private static final String API_KEY = "5tiDiv3NI0U107lFfBdenGZNkdNElakAdFuCd3mQGW3S9IbI7gQNHLLLMFEfwy9h";
    private static final String SECRET_KEY = "TVPiuFyWkundIXJrXbce4TXwkhDWmoMm0xBe124ETr0rP8NK7yCrYL9ZqHnEK6Nn";
    private static final String BASE_URL = "https://api.binance.com";
    public static void main(String[] args) throws Exception {
        System.out.println("Fetching account balance...");
        List<BinanceAccount> accountList = getAccountBalance();


        System.out.println("\nFetching trade orders (specify a symbol if needed, e.g., BTCUSDT)...");
        getTradeOrders(null);  // 可以指定交易对，例如"BTCUSDT"
    }

    private static   List<BinanceAccount>  getAccountBalance() throws Exception {
        String accountBalance = queryBinance("/api/v3/account", null);
        JSONObject account = JSONUtil.parseObj(accountBalance);
        JSONArray balances = account.getJSONArray("balances");

        List<BinanceAccount> accountList=new ArrayList<>();
        for (int i = 0; i < balances.size(); i++) {
            JSONObject o = (JSONObject) balances.get(i);
            String asset = o.get("asset").toString();
            Float free = o.getFloat("free");
            Float locked = o.getFloat("locked");
            if(free!=0){
                BinanceAccount a=new BinanceAccount();
                a.setAsset(asset);
                a.setFree(free);
                a.setLocked(locked);
                accountList.add(a);
            }
        }
        return accountList;
    }

    private static void getTradeOrders(String symbol) throws Exception {
        String extraParams = (symbol != null) ? "symbol=" + URLEncoder.encode(symbol, StandardCharsets.UTF_8.toString()) : "";
        queryBinance("/api/v3/allOrders", extraParams);
    }

    private static String  queryBinance(String endpoint, String extraParams) throws Exception {
        long timestamp = System.currentTimeMillis();
        String queryString = "timestamp=" + timestamp + (extraParams != null && !extraParams.isEmpty() ? "&" + extraParams : "");
        String signature = hmacSHA256Digest(queryString, SECRET_KEY);

        String url = BASE_URL + endpoint + "?" + queryString + "&signature=" + signature;
        String s = makeApiCall(url);
        return s;
    }

    private static String hmacSHA256Digest(String data, String secret) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        sha256_HMAC.init(secretKeySpec);
        byte[] hash = sha256_HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hash);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private static String  makeApiCall(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet request = new HttpGet(url);
            request.setHeader("X-MBX-APIKEY", API_KEY);
            CloseableHttpResponse response = httpClient.execute(request);
            String json_string = EntityUtils.toString(response.getEntity());
            return  json_string;
        } finally {
            httpClient.close();
        }
    }
    @Override
    public List<BinanceAccount> getAccountInfo() {


        try {
            List<BinanceAccount> accountBalance = getAccountBalance();
            System.out.println(accountBalance);
            return accountBalance;
        }catch (Exception e){
            log.error("获得账户信息异常",e);
            e.printStackTrace();
        }
        return  new ArrayList<>();
    }

    @Override
    public boolean startQBot(String id) {



        return false;
    }
}
