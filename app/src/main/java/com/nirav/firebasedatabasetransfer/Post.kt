package com.nirav.firebasedatabasetransfer

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Post")
data class Post(
    @PrimaryKey
    @ColumnInfo("dateTime")
    val dateTime: Long? = null,

    @ColumnInfo("mediaLink")
    val mediaLink: String? = null,

    @ColumnInfo("videoThumbnail")
    val videoThumbnail: String? = null
)
