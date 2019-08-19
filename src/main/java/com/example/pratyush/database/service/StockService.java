package com.example.pratyush.database.service;

import static org.jooq.generated.tables.Stock.*;

import com.example.pratyush.database.utils.JooqUtils;
import org.jooq.DSLContext;
import org.jooq.generated.tables.pojos.Stock;
import org.jooq.impl.DSL;

import java.util.List;

public class StockService implements IStockService {
    private StockService() {}

    public static IStockService getInstance() {
        return new StockService();
    }

    @Override
    public Stock addStock(Stock stock) throws Exception {
        DSLContext dbh = DSL.using(JooqUtils.getJooqConfigurations());
        Stock addedStock = dbh
                .insertInto(STOCK)
                .set(STOCK.SYMBOL, stock.getSymbol())
                .set(STOCK.NAME, stock.getName())
                .set(STOCK.PRICE, stock.getPrice())
                .returning(STOCK.ID)
                .fetchOne()
                .into(Stock.class);
        dbh.close();
        return addedStock;
    }

    @Override
    public int deleteStock(int stockId) throws Exception {
        DSLContext dbh = DSL.using(JooqUtils.getJooqConfigurations());
        int executed = dbh
                .deleteFrom(STOCK)
                .where(STOCK.ID.eq(stockId))
                .execute();
        dbh.close();
        return executed;
    }

    @Override
    public Stock getStock(int stockId) throws Exception {
        DSLContext dbh = DSL.using(JooqUtils.getJooqConfigurations());
        Stock stock = dbh
                .select(
                        STOCK.ID,
                        STOCK.SYMBOL,
                        STOCK.NAME,
                        STOCK.PRICE,
                        STOCK.DELETED
                )
                .from(STOCK)
                .where(STOCK.ID.eq(stockId))
                .fetchOne()
                .into(Stock.class);
        dbh.close();
        return stock;
    }

    @Override
    public List<Stock> getStocks() throws Exception {
        DSLContext dbh = DSL.using(JooqUtils.getJooqConfigurations());
        List<Stock> stocks = dbh
                .select(
                        STOCK.ID,
                        STOCK.SYMBOL,
                        STOCK.NAME,
                        STOCK.PRICE,
                        STOCK.DELETED
                )
                .from(STOCK)
                .fetch()
                .into(Stock.class);
        dbh.close();
        return stocks;
    }

    @Override
    public int updateStock(Stock stock) throws Exception {
        DSLContext dbh = DSL.using(JooqUtils.getJooqConfigurations());
        int executed = dbh
                .update(STOCK)
                .set(STOCK.SYMBOL, stock.getSymbol())
                .set(STOCK.NAME, stock.getName())
                .set(STOCK.PRICE, stock.getPrice())
                .where(STOCK.ID.eq(stock.getId()))
                .execute();
        dbh.close();
        return executed;
    }
}
