package com.ghosts.of.history.utils

import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.firestore


// onSuccessCallback processes an in-storage-path of this video
fun processVideoPathByName(videoName: String, onSuccessCallback: (String?) -> Unit) {
    Firebase.firestore.collection("StorageLinks").whereEqualTo("id", videoName).get().addOnSuccessListener {docs ->
        val firstDoc = if (docs.documents.size > 0) {docs.documents[0]} else {null}
        onSuccessCallback(firstDoc?.get("in_storage_path") as String?)
    }
}

// onSuccessCallback processes just a video name
fun processAnchorDescription(anchorId: String, onSuccessCallback: (String?) -> Unit) {
    Firebase.firestore.collection("AnchorBindings").whereEqualTo("id", anchorId).get().addOnSuccessListener {docs ->
        val firstDoc = if (docs.documents.size > 0) {docs.documents[0]} else {null}
        onSuccessCallback(firstDoc?.get("video_name") as String?)
    }
}

fun processAnchorSets(setName: String, onSuccessCallback: (Array<String>?) -> Unit) {
    Firebase.firestore.collection("AnchorSets").whereEqualTo("name", setName).get().addOnSuccessListener {docs ->
        val firstDoc = if (docs.documents.size > 0) {docs.documents[0]} else {null}
        onSuccessCallback(firstDoc?.get("anchor_ids") as Array<String>?)
    }
}
