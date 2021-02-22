package com.android.deepanshunamdeo.shoppingapp.di

import android.content.Context
import androidx.room.Room
import com.android.deepanshunamdeo.shoppingapp.data.database.ShoppingAppDatabase
import com.android.deepanshunamdeo.shoppingapp.data.model.dao.CartIemDao
import com.android.deepanshunamdeo.shoppingapp.data.model.dao.ShoppingItemDao
import com.android.deepanshunamdeo.shoppingapp.data.repository.CartItemRepository
import com.android.deepanshunamdeo.shoppingapp.data.repository.ShoppingItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): ShoppingAppDatabase =
        Room.databaseBuilder(context, ShoppingAppDatabase::class.java, "shoppingAppDatabase")
            .build()

    @Provides
    fun provideShoppingItemDao(shoppingAppDatabase: ShoppingAppDatabase): ShoppingItemDao =
        shoppingAppDatabase.getShoppingItemDao()

    @Provides
    fun provideShoppingItemRepository(shoppingItemDao: ShoppingItemDao): ShoppingItemRepository =
        ShoppingItemRepository(shoppingItemDao)

    @Provides
    fun provideCartItemDao(shoppingAppDatabase: ShoppingAppDatabase): CartIemDao =
        shoppingAppDatabase.getCartItemDao()

    @Provides
    fun provideCartItemRepository(cartIemDao: CartIemDao): CartItemRepository =
        CartItemRepository(cartIemDao)
}