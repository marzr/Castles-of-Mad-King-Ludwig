package com.github.marzr.castles.service

import com.github.marzr.castles.dao.MarketDao
import com.github.marzr.castles.game.Market

class MarketDbService(
    private val marketDao: MarketDao,
) {
    fun create(gameId: Long, market: Market) {
        marketDao.create(
            gameId,
            market.tiles[Market.Price.PRICE_1000]?.first?.title,
            market.tiles[Market.Price.PRICE_2000]?.first?.title,
            market.tiles[Market.Price.PRICE_4000]?.first?.title,
            market.tiles[Market.Price.PRICE_6000]?.first?.title,
            market.tiles[Market.Price.PRICE_8000]?.first?.title,
            market.tiles[Market.Price.PRICE_10000]?.first?.title,
            market.tiles[Market.Price.PRICE_15000]?.first?.title,
            market.tiles[Market.Price.PRICE_1000]?.second ?: 0,
            market.tiles[Market.Price.PRICE_2000]?.second ?: 0,
            market.tiles[Market.Price.PRICE_4000]?.second ?: 0,
            market.tiles[Market.Price.PRICE_6000]?.second ?: 0,
            market.tiles[Market.Price.PRICE_8000]?.second ?: 0,
            market.tiles[Market.Price.PRICE_10000]?.second ?: 0,
            market.tiles[Market.Price.PRICE_15000]?.second ?: 0,
        )
    }
}
