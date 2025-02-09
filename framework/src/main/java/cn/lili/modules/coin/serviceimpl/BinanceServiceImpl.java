package cn.lili.modules.coin.serviceimpl;
import cn.lili.modules.coin.entity.ChargeAddress;
import com.alibaba.fastjson.JSON;
import okhttp3.*;
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
import java.util.*;
import java.util.stream.Collectors;

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
        getBinanceChargeAddress("ETH");
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


    private static String getBinanceChargeAddress(String symbol) throws Exception {
        String url = "https://api.binance.com/sapi/v1/capital/deposit/address";
        String coin = "USDT"; // 你想获取充值地址的币种
        String network = "Arbitrum One"; // 充值网络

        Map<String, String> parameters = new LinkedHashMap<>();
        parameters.put("coin", coin);
        parameters.put("timestamp", System.currentTimeMillis() + "");

        String query = urlEncodeUTF8(parameters);
        String signature = hmacSHA256Digest(query, SECRET_KEY);

        String s = makeApiCall(url + "?" + query + "&signature=" + signature);


        return  s;


    }



    public static String urlEncodeUTF8(Map<String, String> map)  {

      try {
          String collect = map.entrySet()
                  .stream()
                  .map(entry -> entry.getKey().toString() + "=" + entry.getValue().toString())
                  .collect(Collectors.joining("&"));

          return  collect;

      }catch (Exception e ){
          e.printStackTrace();
      }

      return "";
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

    @Override
    public ChargeAddress getChargeAddress(String memberId) {

        try {
            String address= this.getBinanceChargeAddress(memberId);
            ChargeAddress chargeAddress = JSON.parseObject(address, ChargeAddress.class);
            return chargeAddress;
        }catch (Exception e ){

            e.printStackTrace();
        }
        return  null;
    }
}
