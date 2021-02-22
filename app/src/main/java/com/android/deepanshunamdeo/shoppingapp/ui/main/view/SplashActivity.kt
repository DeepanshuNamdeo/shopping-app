package com.android.deepanshunamdeo.shoppingapp.ui.main.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.android.deepanshunamdeo.shoppingapp.R
import com.android.deepanshunamdeo.shoppingapp.data.model.ShoppingItem
import com.android.deepanshunamdeo.shoppingapp.ui.main.viewmodel.ShoppingItemViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val shoppingItemViewModel: ShoppingItemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        loadDummyData()

        startActivity(Intent(this, ShoppingHomeActivity::class.java))
        finish()
    }

    private fun loadDummyData() {
        var shoppingItem: ArrayList<ShoppingItem> = ArrayList()
        shoppingItem.add(
            ShoppingItem(
                1, "guitar",
                "The guitar is a string instrument which is played by plucking the strings",
                "https://i.ibb.co/5Kgp1fz/guitar.jpg",
                5000
            )
        )
        shoppingItem.add(
            ShoppingItem(
                2, "keyboard",
                "An electronic musical instrument that can produce a wide variety of different sounds",
                "https://i.ibb.co/Q6tpm5G/keyboard.jpg",
                7000
            )
        )
        shoppingItem.add(
            ShoppingItem(
                3, "maracas",
                "A percussion instrument in the form of a hollow gourd or gourd-shaped container filled with dried beans",
                "https://i.ibb.co/sHmHqKr/maracas.jpg",
                500
            )
        )
        shoppingItem.add(
            ShoppingItem(
                4, "piano",
                "A large musical instrument that you play by pressing down black and white keys",
                "https://i.ibb.co/ZBF6HCB/piano.jpg",
                15000
            )
        )
        shoppingItem.add(
            ShoppingItem(
                5, "recorder",
                "a machine for recording sound and/or pictures",
                "https://i.ibb.co/56Yg9jS/recorder.jpg",
                10000
            )
        )
        shoppingItem.add(
            ShoppingItem(
                6, "sitar",
                "The guitar is a string instrument which is played by plucking the strings",
                "https://i.ibb.co/FX86G4d/sitar.jpg",
                5000
            )
        )

        for (items: ShoppingItem in shoppingItem) {
            shoppingItemViewModel.insertShoppingItems(items)
        }

    }
}