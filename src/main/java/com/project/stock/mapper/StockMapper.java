package com.project.stock.mapper;

import com.project.stock.dto.stocks.StockDTO;
import com.project.stock.dto.stocks.WatchlistDTO;
import com.project.stock.model.stocks.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Mapper(componentModel = "spring")
public interface StockMapper {
    @Mapping(target = "lastUpdateUtc", expression = "java(String.valueOf(user.getLastUpdateUtc()))")
    StockDTO mapEntityToResponse(Stock user);

    @Mapping(target = "lastUpdateUtc", expression = "java(mapStringToTimestamp(String.valueOf(stockDTO.getLastUpdateUtc())))")
    Stock mapRequestToEntity(StockDTO stockDTO) throws RuntimeException;


    default Timestamp mapStringToTimestamp(String value) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Define your date format
            Date parsedDate = dateFormat.parse(value);
            return new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            // Handle parsing exception
            e.printStackTrace();
            return null;
        }
    }
}
