package com.example.pratyush.database.service;

import org.jooq.generated.tables.pojos.Trade;

import java.util.List;

public interface ITradeService {
    Trade addTrade(Trade trade) throws Exception;
    int deleteTrade(int tradeId) throws Exception;
    int deleteTrades() throws Exception;
    Trade getTrade(int tradeId) throws Exception;
    List<Trade> getTrades() throws Exception;
    int updateTrade(Trade trade) throws Exception;
}
