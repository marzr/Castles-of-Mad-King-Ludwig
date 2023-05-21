package com.github.marzr.castles

import org.junit.jupiter.api.BeforeAll

interface DbTest {

    companion object {
        @BeforeAll
        @JvmStatic
        fun before() {
            Database().initDatabase
        }
    }
}
