package com.money.pequenoinvestidor.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@ToString
public class Cripto {
    public String id;
    public String symbol;
    public String name;
    public String nameid;
    public int rank;
    public String price_usd;
    public String percent_change_24h;
    public String percent_change_1h;
    public String percent_change_7d;
    public String market_cap_usd;
    public String volume24;
    public String volume24_native;
    public String csupply;
    public String price_btc;
    public String tsupply;
    public String msupply;

    @Override
    public String toString() {
        return "CriptoMoeda [name=" + name + ", price_usd=" + price_usd + ", percent_change_1h=" + percent_change_1h + "]";
    }

    public static int getCriptoCode(String codigo){
        switch (codigo){
            case "BTH": return 90;
            case "ETH": return 80;
            case "BNB": return 2710;
            case "XRP": return 58;
            case "DOGE": return 2;
            case "DOT": return 45219;
            case "ADA": return 257;
            case "LTC": return 1;
            case "BCH": return  0;
        }
        return 0;
    }
}
