/*
 * This file is generated by jOOQ.
 */
package org.jooq.generated;


import javax.annotation.Generated;

import org.jooq.generated.tables.Stock;
import org.jooq.generated.tables.Trade;
import org.jooq.generated.tables.Trader;


/**
 * Convenience access to all tables in public
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>public.stock</code>.
     */
    public static final Stock STOCK = org.jooq.generated.tables.Stock.STOCK;

    /**
     * The table <code>public.trade</code>.
     */
    public static final Trade TRADE = org.jooq.generated.tables.Trade.TRADE;

    /**
     * The table <code>public.trader</code>.
     */
    public static final Trader TRADER = org.jooq.generated.tables.Trader.TRADER;
}