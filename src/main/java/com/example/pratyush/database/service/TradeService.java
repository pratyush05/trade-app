package com.example.pratyush.database.service;

import static org.jooq.generated.tables.Trade.*;

import com.example.pratyush.database.utils.JooqUtils;
import org.jooq.DSLContext;
import org.jooq.generated.tables.pojos.Trade;
import org.jooq.impl.DSL;

import java.util.List;

public class TradeService implements ITradeService {
    private TradeService() {}

    public static ITradeService getInstance() {
        return new TradeService();
    }

    @Override
    public Trade addTrade(Trade trade) throws Exception {
        DSLContext dbh = DSL.using(JooqUtils.getJooqConfigurations());
        Trade addedTrade = dbh
                .insertInto(TRADE)
                .set(TRADE.TYPE, trade.getType())
                .set(TRADE.PRICE, trade.getPrice())
                .set(TRADE.SHARES, trade.getShares())
                .set(TRADE.STOCKID, trade.getStockid())
                .set(TRADE.TRADERID, trade.getTraderid())
                .set(TRADE.CREATED, trade.getCreated())
                .returning(TRADE.ID)
                .fetchOne()
                .into(Trade.class);
        dbh.close();
        return addedTrade;
    }

    @Override
    public int deleteTrade(int tradeId) throws Exception {
        DSLContext dbh = DSL.using(JooqUtils.getJooqConfigurations());
        int executed = dbh
                .deleteFrom(TRADE)
                .where(TRADE.ID.eq(tradeId))
                .execute();
        dbh.close();
        return executed;
    }

    @Override
    public int deleteTrades() throws Exception {
        DSLContext dbh = DSL.using(JooqUtils.getJooqConfigurations());
        int executed = dbh
                .deleteFrom(TRADE)
                .execute();
        dbh.close();
        return executed;
    }

    @Override
    public Trade getTrade(int tradeId) throws Exception {
        DSLContext dbh = DSL.using(JooqUtils.getJooqConfigurations());
        Trade trade = dbh
                .select(
                        TRADE.ID,
                        TRADE.TYPE,
                        TRADE.PRICE,
                        TRADE.SHARES,
                        TRADE.STOCKID,
                        TRADE.TRADERID,
                        TRADE.CREATED,
                        TRADE.DELETED
                )
                .from(TRADE)
                .where(TRADE.ID.eq(tradeId))
                .fetchOne()
                .into(Trade.class);
        dbh.close();
        return trade;
    }

    @Override
    public List<Trade> getTrades() throws Exception {
        DSLContext dbh = DSL.using(JooqUtils.getJooqConfigurations());
        List<Trade> trades = dbh
                .select(
                        TRADE.ID,
                        TRADE.TYPE,
                        TRADE.PRICE,
                        TRADE.SHARES,
                        TRADE.STOCKID,
                        TRADE.TRADERID,
                        TRADE.CREATED,
                        TRADE.DELETED
                )
                .from(TRADE)
                .fetch()
                .into(Trade.class);
        dbh.close();
        return trades;
    }

    @Override
    public int updateTrade(Trade trade) throws Exception {
        DSLContext dbh = DSL.using(JooqUtils.getJooqConfigurations());
        int executed = dbh
                .update(TRADE)
                .set(TRADE.TYPE, trade.getType())
                .set(TRADE.PRICE, trade.getPrice())
                .set(TRADE.SHARES, trade.getShares())
                .set(TRADE.STOCKID, trade.getStockid())
                .set(TRADE.TRADERID, trade.getTraderid())
                .where(TRADE.ID.eq(trade.getId()))
                .execute();
        dbh.close();
        return executed;
    }
}
