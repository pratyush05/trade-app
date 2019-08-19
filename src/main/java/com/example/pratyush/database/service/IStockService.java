package com.example.pratyush.database.service;

import org.jooq.generated.tables.pojos.Stock;

import java.util.List;

public interface IStockService {
    Stock addStock(Stock stock) throws Exception;
    int deleteStock(int stockId) throws Exception;
    Stock getStock(int stockId) throws Exception;
    List<Stock> getStocks() throws Exception;
    int updateStock(Stock stock) throws Exception;
}
