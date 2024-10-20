package com.example.tp2_mobileprogramming

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "VisitBali.db"
        private const val TABLE_DESTINATIONS = "destinations"
        private const val KEY_ID = "id"
        private const val KEY_NAME = "name"
        private const val KEY_SHORT_DESCRIPTION = "short_description"
        private const val KEY_LONG_DESCRIPTION = "long_description"
        private const val KEY_IMAGE_RES_ID = "image_res_id"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLE_DESTINATIONS (" +
                "$KEY_ID INTEGER PRIMARY KEY," +
                "$KEY_NAME TEXT," +
                "$KEY_SHORT_DESCRIPTION TEXT," +
                "$KEY_LONG_DESCRIPTION TEXT," +
                "$KEY_IMAGE_RES_ID INTEGER)"
        db.execSQL(createTable)

        // Insert initial data
        insertDestination(db, Destination(
            "Ubud",
            "Cultural heart of Bali",
            "Ubud, often referred to as the cultural heart of Bali, is a enchanting town nestled amidst lush rainforests and terraced rice paddies. Known for its traditional crafts and dance, Ubud has long been a haven for artists and seekers of Balinese culture.\n\nThe town is home to numerous art galleries, traditional craft shops, and the sacred Monkey Forest, a nature reserve and temple complex that houses hundreds of mischievous long-tailed macaques. Visitors can explore the Ubud Royal Palace, witness mesmerizing traditional dance performances, or take a stroll through the iconic Tegalalang Rice Terrace.\n\nUbud is also a center for yoga and wellness, with many retreat centers and spas offering rejuvenating experiences. Food enthusiasts will delight in the town's culinary scene, ranging from traditional warungs serving local delicacies to world-class restaurants offering innovative cuisine. Whether you're seeking cultural immersion, spiritual reflection, or simply a peaceful escape, Ubud offers a uniquely Balinese experience that captivates the soul.",
            R.drawable.ubud
        ))
        insertDestination(db, Destination(
            "Canggu",
            "Surf and hipster paradise",
            "Canggu, a coastal village on Bali's southern coast, has rapidly transformed from a sleepy surf spot to a trendy hotspot that perfectly balances Balinese tradition with modern hipster culture. Known for its excellent surf breaks, Canggu attracts wave riders from around the world to its black-sand beaches.\n\nBeyond surfing, Canggu has become synonymous with a laid-back, health-conscious lifestyle. The area is dotted with numerous yoga studios, vegan cafes, and organic restaurants catering to the wellness-focused crowd. Instagram-worthy cafes and beach clubs line the streets, offering everything from smoothie bowls to sunset cocktails with ocean views.\n\nDespite its rapid development, Canggu still retains pockets of traditional Balinese life. Visitors can explore local temples, watch farmers tend to emerald rice paddies, or witness stunning sunsets at Echo Beach. Whether you're a digital nomad looking for a co-working space, a surf enthusiast chasing waves, or a traveler seeking a blend of tradition and trend, Canggu offers a unique and vibrant Bali experience.",
            R.drawable.canggu
        ))
        insertDestination(db, Destination(
            "Kuta",
            "Famous beach and nightlife",
            "Kuta, one of Bali's most famous beach destinations, is renowned for its long stretch of golden sand, spectacular sunsets, and vibrant nightlife. Once a quiet fishing village, Kuta has evolved into a bustling tourist hub that attracts visitors from all corners of the globe.\n\nThe beach is the centerpiece of Kuta, offering a perfect setting for sunbathing, swimming, and learning to surf. As the sun sets, Kuta transforms into a pulsating center of nightlife, with numerous bars, clubs, and live music venues keeping the party going until the early hours.\n\nBeyond the beach and nightlife, Kuta offers a wide array of shopping experiences, from traditional markets to modern malls. The area is also home to the Waterbom Bali water park, offering a fun-filled day for families. While Kuta's rapid development has changed its landscape, it remains a place where tourists can experience the warmth of Balinese hospitality and the excitement of a beach town that never sleeps.",
            R.drawable.kuta
        ))
        insertDestination(db, Destination(
            "Jimbaran",
            "Seafood dining on the beach",
            "Jimbaran, a tranquil fishing village turned upscale beach resort area, is famous for its pristine bay, luxury resorts, and most notably, its beachfront seafood dining experience. Located on Bali's southwestern coast, Jimbaran offers a more relaxed atmosphere compared to its bustling neighbors.\n\nThe highlight of Jimbaran is undoubtedly its seafood restaurants that line the beach. As the sun sets, the beach comes alive with countless candlelit tables where diners can feast on freshly caught seafood with their toes in the sand. The breathtaking sunset views add to the romantic ambiance, making it a favorite spot for couples.\n\nBeyond its culinary fame, Jimbaran boasts some of Bali's most beautiful beaches. The calm waters of Jimbaran Bay are perfect for swimming and sunbathing. The area is also home to high-end resorts and spas, offering luxurious accommodations and wellness treatments. For those interested in local life, a visit to the bustling fish market in the early morning provides insight into the village's fishing heritage.",
            R.drawable.jimbaran
        ))
        insertDestination(db, Destination(
            "Uluwatu",
            "Clifftop temples and surf spots",
            "Uluwatu, perched on the southwestern tip of Bali's Bukit Peninsula, is a dramatic landscape of towering limestone cliffs meeting the vast Indian Ocean. This area is renowned for its stunning clifftop temple, world-class surf breaks, and breathtaking coastal views.\n\nThe crown jewel of Uluwatu is the ancient Pura Luhur Uluwatu temple. Sitting on a 70-meter-high cliff, this 11th-century temple not only offers spectacular ocean views but is also an important spiritual site for the Balinese. At sunset, visitors can witness the mesmerizing Kecak fire dance performance with the temple and setting sun as a backdrop.\n\nFor surf enthusiasts, Uluwatu is a paradise. The area boasts several world-renowned surf spots catering to different skill levels. Even for non-surfers, the beaches of Uluwatu, such as Padang Padang and Bingin, offer picturesque settings for relaxation and sunbathing. The area has also seen the rise of cliff-top bars and restaurants, where visitors can enjoy panoramic ocean views while sipping cocktails or enjoying gourmet meals.",
            R.drawable.uluwatu
        ))
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_DESTINATIONS")
        onCreate(db)
    }

    private fun insertDestination(db: SQLiteDatabase, destination: Destination) {
        val values = ContentValues().apply {
            put(KEY_NAME, destination.name)
            put(KEY_SHORT_DESCRIPTION, destination.shortDescription)
            put(KEY_LONG_DESCRIPTION, destination.longDescription)
            put(KEY_IMAGE_RES_ID, destination.imageResId)
        }
        db.insert(TABLE_DESTINATIONS, null, values)
    }

    fun getAllDestinations(): List<Destination> {
        val destinations = mutableListOf<Destination>()
        val selectQuery = "SELECT * FROM $TABLE_DESTINATIONS"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        cursor.use {
            if (it.moveToFirst()) {
                do {
                    val name = it.getString(it.getColumnIndex(KEY_NAME))
                    val shortDescription = it.getString(it.getColumnIndex(KEY_SHORT_DESCRIPTION))
                    val longDescription = it.getString(it.getColumnIndex(KEY_LONG_DESCRIPTION))
                    val imageResId = it.getInt(it.getColumnIndex(KEY_IMAGE_RES_ID))
                    destinations.add(Destination(name, shortDescription, longDescription, imageResId))
                } while (it.moveToNext())
            }
        }
        return destinations
    }
}