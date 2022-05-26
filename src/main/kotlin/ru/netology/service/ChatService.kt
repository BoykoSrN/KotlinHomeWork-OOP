package ru.netology.service

import ru.netology.data.Chat
import ru.netology.data.Message

object ChatService {
    var chats = mutableListOf<Chat>()


    fun sendMessage(user1Id: Int, user2Id: Int, text: String) {
        val id = 0
        val message = Message(1, user1Id, text)
        val newChat = chats.firstOrNull { chat ->
            chat.users.containsAll(listOf(user1Id, user2Id))
        }.let { chat ->
            chat?.copy(messages = chat.messages + message)
        } ?: Chat(
            id + 1, listOf(user1Id, user2Id), listOf(message)
        )
        chats.removeIf { newChat.id == it.id }
        chats.add(newChat)
    }

    fun getChats(userId: Int) =
        chats.filter { chat ->
            chat.users.contains(userId)
        }
            .map { chat ->
                chat.messages
                    .map { message ->
                        message.copy(isRead = true)
                    }
            }


    fun readChat(chatId: Int): Boolean {
        chats.map { chat ->
            if (chat.id == chatId) chat.messages
                .map {
                    it.isRead = true
                    return true
                }
        }
        return false
    }

    fun getUnreadChatsCount(ownerId: Int): Int {
        var unreadChatsNumber = 0
        chats.forEach { chat ->
            val lastIndex: Int = chat.messages.lastIndex
            if (chat.messages[lastIndex].ownerId != ownerId || !chat.messages[lastIndex].isRead) {
                unreadChatsNumber++
            }
        }
        return unreadChatsNumber
    }


    fun deleteChat(chatId: Int) = chats
        .filter { chat -> chat.id == chatId }
        .map { chat ->
            chat.copy(
                id = null,
                users = emptyList(),
                messages = emptyList()
            )
        }

    fun editMessage(chatId: Int, messageId: Int, text: String) = chats.filter { chat ->
        chat.id == chatId
    }
        .map { chat ->
            chat.messages
                .filter { message -> message.id == messageId }
                .map { message -> message.copy(message = text) }
        }

    fun deleteMessage(chatId: Int, messageId: Int): Boolean {
        chats.map { chat -> chat.messages.size }
        chats.filter { chat -> chat.id == chatId }
            .map { chat ->
                chat.messages
                    .map { message ->
                        if (message.id == messageId) message.message = null
                        return true
                    }
                if (chat.messages.isEmpty()) {
                    deleteChat(chatId)
                    return true
                }
            }
        return false
    }

    fun clear() {
        chats.map { chat ->
            chat.copy (
                id = null,
                users = emptyList(),
                messages = emptyList()
            )

        }
    }
}