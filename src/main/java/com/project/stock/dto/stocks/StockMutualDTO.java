package com.project.stock.dto.stocks;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class StockMutualDTO {

    public String status;
    public String request_id;
    public SearchData data;
}

@Data
class SearchData{
    public ArrayList<StockDTO> stock;
    @JsonProperty("ETF")
    public ArrayList<Object> eTF;
    public ArrayList<Object> index;
    public ArrayList<MutualFundDTO> mutual_fund;
    public ArrayList<Object> currency;
    public ArrayList<Object> futures;
}





