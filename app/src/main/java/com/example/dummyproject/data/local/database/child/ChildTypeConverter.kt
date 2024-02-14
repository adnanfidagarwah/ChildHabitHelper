package com.example.dummyproject.data.local.database.child

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import com.example.dummyproject.presentation.util.observer.toJson
import com.example.dummyproject.presentation.util.observer.toObject
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.ByteArrayOutputStream

class ChildTypeConverter {

    val gson = Gson()

    @TypeConverter
    fun childToString(repositoriesResponse: Child): String =
        repositoriesResponse.toJson()

    @TypeConverter
    fun stringToChild(data: String): Child = data.toObject()


    @TypeConverter
    fun itemToString(item: List<Child>): String = item.toJson()

    @TypeConverter
    fun stringToItem(data: String): List<Child> {
        val myType = object : TypeToken<List<Child>>() {}.type
        return gson.fromJson(data, myType)
    }


    @TypeConverter
    fun fromBitmap(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }

    @TypeConverter
    fun toBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

//
//    @TypeConverter
//    fun licenseToString(license: RepositoriesResponse.Item.License): String = license.toJson()
//
//    @TypeConverter
//    fun stringToLicense(data: String): RepositoriesResponse.Item.License = data.toObject()
//
//    @TypeConverter
//    fun ownerToString(owner: RepositoriesResponse.Item.Owner): String = owner.toJson()
//
//    @TypeConverter
//    fun stringToOwner(data: String): RepositoriesResponse.Item.Owner = data.toObject()


}