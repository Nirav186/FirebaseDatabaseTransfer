package com.nirav.firebasedatabasetransfer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    fun postDataTransfer() {
        viewModelScope.launch {
            FirebaseFirestore.getInstance().collection("post").get().addOnCompleteListener {
                if (it.isSuccessful) {
                    val querySnapshot: QuerySnapshot? = it.result
                    val posts: MutableList<Post> = mutableListOf()
                    querySnapshot?.forEach { documentSnapshot ->
                        val post: Post = documentSnapshot.toObject(Post::class.java)
                        post.let {
                            posts.add(it)
                        }
                    }
                    addPostDataToRealTimeDatabase(posts)
                }
            }
        }
    }

    private fun addPostDataToRealTimeDatabase(posts: MutableList<Post>) {
        val ref = Firebase.database.reference.child("post")
        posts.forEach { post ->
            ref.child(post.dateTime.toString()).setValue(post)
        }
    }

    fun dailyThoughtsDataTransfer() {
        viewModelScope.launch {
            FirebaseFirestore.getInstance().collection("dailyThought").get().addOnCompleteListener {
                if (it.isSuccessful) {
                    val querySnapshot: QuerySnapshot? = it.result
                    val posts: MutableList<Post> = mutableListOf()
                    querySnapshot?.forEach { documentSnapshot ->
                        val post: Post = documentSnapshot.toObject(Post::class.java)
                        post.let {
                            posts.add(it)
                        }
                    }
                    addDailyThoughtsDataToRealTimeDatabase(posts)
                }
            }
        }
    }

    private fun addDailyThoughtsDataToRealTimeDatabase(posts: MutableList<Post>) {
        val ref = Firebase.database.reference.child("dailyThought")
        posts.forEach { post ->
            ref.child(post.dateTime.toString()).setValue(post)
        }
    }

    fun addAdsVariable() {
        Firebase.database.reference.child("Ads").setValue(Ads())
    }

}