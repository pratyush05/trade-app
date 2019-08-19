package com.example.pratyush.database.service;

import static org.jooq.generated.tables.Trader.*;

import com.example.pratyush.database.utils.JooqUtils;
import org.jooq.DSLContext;
import org.jooq.generated.tables.pojos.Trader;
import org.jooq.impl.DSL;

import java.util.List;

public class TraderService implements ITraderService {
    private TraderService() {}

    public static ITraderService getInstance() {
        return new TraderService();
    }

    @Override
    public Trader addTrader(Trader trader) throws Exception {
        DSLContext dbh = DSL.using(JooqUtils.getJooqConfigurations());
        Trader addedTrader = dbh
                .insertInto(TRADER)
                .set(TRADER.NAME, trader.getName())
                .returning(TRADER.ID)
                .fetchOne()
                .into(Trader.class);
        dbh.close();
        return addedTrader;
    }

    @Override
    public int deleteTrader(int traderId) throws Exception {
        DSLContext dbh = DSL.using(JooqUtils.getJooqConfigurations());
        int executed = dbh
                .deleteFrom(TRADER)
                .where(TRADER.ID.eq(traderId))
                .execute();
        dbh.close();
        return executed;
    }

    @Override
    public Trader getTrader(int traderId) throws Exception {
        DSLContext dbh = DSL.using(JooqUtils.getJooqConfigurations());
        Trader trader = dbh
                .select(
                        TRADER.ID,
                        TRADER.NAME,
                        TRADER.DELETED
                )
                .from(TRADER)
                .where(TRADER.ID.eq(traderId))
                .fetchOne()
                .into(Trader.class);
        dbh.close();
        return trader;
    }

    @Override
    public List<Trader> getTraders() throws Exception {
        DSLContext dbh = DSL.using(JooqUtils.getJooqConfigurations());
        List<Trader> traders = dbh
                .select(
                        TRADER.ID,
                        TRADER.NAME,
                        TRADER.DELETED
                )
                .from(TRADER)
                .fetch()
                .into(Trader.class);
        dbh.close();
        return traders;
    }

    @Override
    public int updateTrader(Trader trader) throws Exception {
        DSLContext dbh = DSL.using(JooqUtils.getJooqConfigurations());
        int executed = dbh
                .update(TRADER)
                .set(TRADER.NAME, trader.getName())
                .where(TRADER.ID.eq(trader.getId()))
                .execute();
        dbh.close();
        return executed;
    }
}
