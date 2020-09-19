package com.android.whatsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.whatsapp.models.Message
import com.android.whatsapp.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import com.vanniktech.emoji.EmojiManager
import com.vanniktech.emoji.google.GoogleEmojiProvider
import kotlinx.android.synthetic.main.activity_chat.*

const val UID = "uid"
const val NAME = "name"
const val IMAGE = "photo"

class ChatActivity : AppCompatActivity() {

    private val friendId:String by lazy {
        intent.getStringExtra(UID)
    }
    private val name:String by lazy {
        intent.getStringExtra(NAME)
    }
    private val image:String by lazy {
        intent.getStringExtra(IMAGE)
    }
    private val mCurrentUid: String by lazy {
        FirebaseAuth.getInstance().uid!!
    }
    private val db: FirebaseDatabase by lazy {
        FirebaseDatabase.getInstance()
    }
    lateinit var currentUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EmojiManager.install(GoogleEmojiProvider())
        setContentView(R.layout.activity_chat)

        FirebaseFirestore.getInstance().collection("users").document(mCurrentUid).get()
            .addOnSuccessListener {
                currentUser = it.toObject(User::class.java)!!
            }

        nameTv.text = name
        Picasso.get().load(image).into(userImgView)

        sendBtn.setOnClickListener {
            msgEdtv.text?.let {
                if(it.isNotEmpty()) {
                    sendMessage(it.toString())
                    it.clear()
                }
            }
        }
    }

    private fun sendMessage(msg: String) {
        val id = getMessages(friendId).push().key  //uniquekey
        checkNotNull(id) { "Cannot be null" }
        val msgMap = Message(msg,mCurrentUid,id)
        getMessages(friendId).child(id).setValue(msgMap).addOnSuccessListener {

        }.addOnFailureListener {

        }
    }

    private fun getMessages(friendId: String) =
        db.reference.child("messages/${getId(friendId)}")

    private fun getInbox(toUser: String, fromUser:String) =
        db.reference.child("chats/$toUser/$fromUser")

    private fun getId(friendId: String): String { //get Id for the messages
        return if(friendId > mCurrentUid) {
            mCurrentUid + friendId
        } else {
            friendId + mCurrentUid
        }
    }
}