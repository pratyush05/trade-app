--liquibase formatted sql

--changeset gpratyush:1.1.1
INSERT INTO public.stock(symbol, name, price, deleted) VALUES ('AMZN', 'Amazon', 1763.13, null);
INSERT INTO public.stock(symbol, name, price, deleted) VALUES ('AAPL', 'Apple', 193.34, null);
INSERT INTO public.stock(symbol, name, price, deleted) VALUES ('FB', 'Facebook', 181.73, null);
INSERT INTO public.stock(symbol, name, price, deleted) VALUES ('GOOGL', 'Google', 1154.75, null);
INSERT INTO public.stock(symbol, name, price, deleted) VALUES ('MSFT', 'Microsoft', 132.21, null);

--changeset gpratyush:1.1.2
INSERT INTO public.trader(name, deleted) VALUES ('Jeff Bezos', null);
INSERT INTO public.trader(name, deleted) VALUES ('Steve Wozniak', null);
INSERT INTO public.trader(name, deleted) VALUES ('Mark Zuckerberg', null);
INSERT INTO public.trader(name, deleted) VALUES ('Larry Page', null);
INSERT INTO public.trader(name, deleted) VALUES ('Bill Gates', null);

--changeset gpratyush:1.1.3
INSERT INTO public.trade(type, price, shares, stockid, traderid, created, deleted) VALUES ('Buy', 1800.53, 432, 1, 1, NOW()::timestamp, null);
INSERT INTO public.trade(type, price, shares, stockid, traderid, created, deleted) VALUES ('Buy', 230.21, 500, 2, 2, NOW()::timestamp, null);
INSERT INTO public.trade(type, price, shares, stockid, traderid, created, deleted) VALUES ('Buy', 179.99, 532, 3, 3, NOW()::timestamp, null);
INSERT INTO public.trade(type, price, shares, stockid, traderid, created, deleted) VALUES ('Buy', 1154.50, 342, 4, 4, NOW()::timestamp, null);
INSERT INTO public.trade(type, price, shares, stockid, traderid, created, deleted) VALUES ('Buy', 131.77, 875, 5, 5, NOW()::timestamp, null);
INSERT INTO public.trade(type, price, shares, stockid, traderid, created, deleted) VALUES ('Sell', 1734.53, 432, 1, 1, NOW()::timestamp, null);
INSERT INTO public.trade(type, price, shares, stockid, traderid, created, deleted) VALUES ('Sell', 193.30, 500, 2, 2, NOW()::timestamp, null);
INSERT INTO public.trade(type, price, shares, stockid, traderid, created, deleted) VALUES ('Sell', 182.00, 532, 3, 3, NOW()::timestamp, null);
INSERT INTO public.trade(type, price, shares, stockid, traderid, created, deleted) VALUES ('Sell', 1154.28, 342, 4, 4, NOW()::timestamp, null);
INSERT INTO public.trade(type, price, shares, stockid, traderid, created, deleted) VALUES ('Sell', 131.77, 875, 5, 5, NOW()::timestamp, null);



