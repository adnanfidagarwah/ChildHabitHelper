package com.example.dummyproject.data.local.database.goal

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
/*(
    foreignKeys = [ForeignKey(
        entity = GoalCategory::class,
        parentColumns = ["uid"],
        childColumns = ["goalCategoryId"]
    )]
)*/
data class Goal(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,

    @ColumnInfo(name = "goalCategoryId", index = true) val goalCategoryId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "value") val value: Int,
    @ColumnInfo(name = "unit") val unit: String,
    @ColumnInfo(name = "points") val points: Int,
    @ColumnInfo(name = "icon") val icon: Int
)