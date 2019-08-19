/*
 * This file is generated by jOOQ.
 */
package org.jooq.generated.tables.pojos;


import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.generated.tables.interfaces.ITrade;


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
public class Trade implements ITrade {

    private static final long serialVersionUID = -1204753176;

    private final Integer   id;
    private final String    type;
    private final Double    price;
    private final Integer   shares;
    private final Integer   stockid;
    private final Integer   traderid;
    private final Timestamp created;
    private final Timestamp deleted;

    public Trade(ITrade value) {
        this.id = value.getId();
        this.type = value.getType();
        this.price = value.getPrice();
        this.shares = value.getShares();
        this.stockid = value.getStockid();
        this.traderid = value.getTraderid();
        this.created = value.getCreated();
        this.deleted = value.getDeleted();
    }

    public Trade(
        Integer   id,
        String    type,
        Double    price,
        Integer   shares,
        Integer   stockid,
        Integer   traderid,
        Timestamp created,
        Timestamp deleted
    ) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.shares = shares;
        this.stockid = stockid;
        this.traderid = traderid;
        this.created = created;
        this.deleted = deleted;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public Double getPrice() {
        return this.price;
    }

    @Override
    public Integer getShares() {
        return this.shares;
    }

    @Override
    public Integer getStockid() {
        return this.stockid;
    }

    @Override
    public Integer getTraderid() {
        return this.traderid;
    }

    @Override
    public Timestamp getCreated() {
        return this.created;
    }

    @Override
    public Timestamp getDeleted() {
        return this.deleted;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Trade (");

        sb.append(id);
        sb.append(", ").append(type);
        sb.append(", ").append(price);
        sb.append(", ").append(shares);
        sb.append(", ").append(stockid);
        sb.append(", ").append(traderid);
        sb.append(", ").append(created);
        sb.append(", ").append(deleted);

        sb.append(")");
        return sb.toString();
    }
}
