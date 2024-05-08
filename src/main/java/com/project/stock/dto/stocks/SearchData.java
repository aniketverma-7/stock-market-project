package com.project.stock.dto.stocks;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class SearchData{
    public ArrayList<StockDTO> stock;
    @JsonProperty("ETF")
    public ArrayList<Object> eTF;
    public ArrayList<Object> index;
    @JsonProperty("mutual_fund")
    public ArrayList<MutualFundDTO> mutualFunds;
    public ArrayList<Object> currency;
    public ArrayList<Object> futures;
}
