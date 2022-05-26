package ru.netology.service

import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import ru.netology.data.Message

class ChatServiceTest {

    @After
    fun tearDown() {
        ChatService.clear()
    }

    @Test
    fun sendMessage() {
        ChatService
        val user1Id = 1
        val user2Id = 2
        val text = "1"
        ChatService.sendMessage(user1Id, user2Id, text)
        assertEquals(1, ChatService.chats.size)

    }

    @Test
    fun testGetChats() {
        ChatService
        val user1Id = 1
        val user2Id = 2
        val text = "1"
        ChatService.sendMessage(user1Id, user2Id, text)
        val result = ChatService.getChats(user1Id)
        assertNotEquals(ChatService.chats.map { chat -> chat.messages }, result)
    }

    @Test
    fun readChat() {
        ChatService
        val user1Id = 1
        val user2Id = 2
        val text = "1"
        ChatService.sendMessage(user1Id, user2Id, text)
        ChatService.sendMessage(user1Id, user2Id, text)
        val result = ChatService.readChat(1)
        assertTrue(result)
    }

    @Test
    fun noReadChat() {
        ChatService
        val user1Id = 1
        val user2Id = 2
        val text = "1"
        ChatService.sendMessage(user1Id, user2Id, text)
        ChatService.sendMessage(user1Id, user2Id, text)
        val result = ChatService.readChat(4)
        assertFalse(result)
    }

    @Test
    fun getUnreadChatsCount() {
        ChatService
        val senderId = 1
        val recipientId = 2
        val text = "text"
        ChatService.sendMessage(senderId, recipientId, text)
        assertEquals(1, ChatService.getUnreadChatsCount(recipientId))
    }

    @Test
    fun deleteChat() {
        ChatService
        val user1Id = 1
        val user2Id = 2
        val text = "1"
        ChatService.sendMessage(user1Id, user2Id, text)
        assertNotEquals(ChatService.chats, ChatService.deleteChat(1))
    }


    @Test
    fun editMessage() {
        ChatService
        val senderId = 1
        val recipientId = 2
        val text = "text"
        ChatService.sendMessage(senderId,recipientId,text)
        assertNotEquals(ChatService.chats.map { chat -> chat.messages }, ChatService.editMessage(1,1,"Hello"))
    }

    @Test
    fun deleteMessage() {
        ChatService
        val senderId = 1
        val recipientId = 2
        val text = "text"
        ChatService.sendMessage(senderId,recipientId,text)
        val result = ChatService.deleteMessage(1,1)
        assertTrue(result)
    }

    @Test
    fun deleteMessageFalse() {
        ChatService
        val senderId = 1
        val recipientId = 2
        val text = "text"
        ChatService.sendMessage(senderId,recipientId,text)
        val result = ChatService.deleteMessage(2,1)
        assertFalse(result)
    }

    @Test
    fun deleteMessageIsEmpty() {
        ChatService
        val senderId = 1
        val recipientId = 2
        val text = "text"
        ChatService.sendMessage(senderId,recipientId,text)
        ChatService.chats.map { chat -> chat.messages == mutableListOf<Message>() }
        val result = ChatService.deleteMessage(1,1)
        assertTrue(result)
    }

}