/*
 * This file is generated by jOOQ.
 */
package org.jooq.generated.tables;


import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.generated.Indexes;
import org.jooq.generated.Keys;
import org.jooq.generated.Public;
import org.jooq.generated.tables.records.TradeRecord;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * The table <code>public.trade</code>.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Trade extends TableImpl<TradeRecord> {

    private static final long serialVersionUID = -1570137960;

    /**
     * The reference instance of <code>public.trade</code>
     */
    public static final Trade TRADE = new Trade();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TradeRecord> getRecordType() {
        return TradeRecord.class;
    }

    /**
     * The column <code>public.trade.id</code>.
     */
    public final TableField<TradeRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.trade.type</code>.
     */
    public final TableField<TradeRecord, String> TYPE = createField("type", org.jooq.impl.SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>public.trade.price</code>.
     */
    public final TableField<TradeRecord, Double> PRICE = createField("price", org.jooq.impl.SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>public.trade.shares</code>.
     */
    public final TableField<TradeRecord, Integer> SHARES = createField("shares", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.trade.stockid</code>.
     */
    public final TableField<TradeRecord, Integer> STOCKID = createField("stockid", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.trade.traderid</code>.
     */
    public final TableField<TradeRecord, Integer> TRADERID = createField("traderid", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.trade.created</code>.
     */
    public final TableField<TradeRecord, Timestamp> CREATED = createField("created", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

    /**
     * The column <code>public.trade.deleted</code>.
     */
    public final TableField<TradeRecord, Timestamp> DELETED = createField("deleted", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

    /**
     * Create a <code>public.trade</code> table reference
     */
    public Trade() {
        this(DSL.name("trade"), null);
    }

    /**
     * Create an aliased <code>public.trade</code> table reference
     */
    public Trade(String alias) {
        this(DSL.name(alias), TRADE);
    }

    /**
     * Create an aliased <code>public.trade</code> table reference
     */
    public Trade(Name alias) {
        this(alias, TRADE);
    }

    private Trade(Name alias, Table<TradeRecord> aliased) {
        this(alias, aliased, null);
    }

    private Trade(Name alias, Table<TradeRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Trade(Table<O> child, ForeignKey<O, TradeRecord> key) {
        super(child, key, TRADE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.TRADE_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<TradeRecord> getPrimaryKey() {
        return Keys.TRADE_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TradeRecord>> getKeys() {
        return Arrays.<UniqueKey<TradeRecord>>asList(Keys.TRADE_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<TradeRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<TradeRecord, ?>>asList(Keys.TRADE__TRADE_STOCKID_FKEY, Keys.TRADE__TRADE_TRADERID_FKEY);
    }

    public Stock stock() {
        return new Stock(this, Keys.TRADE__TRADE_STOCKID_FKEY);
    }

    public Trader trader() {
        return new Trader(this, Keys.TRADE__TRADE_TRADERID_FKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Trade as(String alias) {
        return new Trade(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Trade as(Name alias) {
        return new Trade(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Trade rename(String name) {
        return new Trade(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Trade rename(Name name) {
        return new Trade(name, null);
    }
}
