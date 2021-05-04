package com.company;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Main.JsonToJava();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void JsonToJava() throws Exception {
        String url = "https://cat-fact.herokuapp.com/facts";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        System.out.println("-----------------------------------Json------------------------------\n" + response.toString());
        String jsonStr = String.valueOf(response);
        ObjectMapper objectMapper = new ObjectMapper();
        List<UserBean> userBean;
        userBean = objectMapper.readValue(jsonStr, new TypeReference<List<UserBean>>() {
        });
        System.out.println("\n------------------------------------Java classes-----------------------");
        for (UserBean i : userBean) {
            System.out.println(i + "\n");
        }
    }
}
