package ro.twodoors.chatbot.ui

data class DataItem (
    val title: String,
    val position : Int,
    val state : ItemState) {
}

enum class ItemState {
    Start,
    Unlock
}