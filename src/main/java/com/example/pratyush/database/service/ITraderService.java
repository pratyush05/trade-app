package com.example.pratyush.database.service;

import org.jooq.generated.tables.pojos.Trader;

import java.util.List;

public interface ITraderService {
    Trader addTrader(Trader trader) throws Exception;
    int deleteTrader(int traderId) throws Exception;
    Trader getTrader(int traderId) throws Exception;
    List<Trader> getTraders() throws Exception;
    int updateTrader(Trader trader) throws Exception;
}
