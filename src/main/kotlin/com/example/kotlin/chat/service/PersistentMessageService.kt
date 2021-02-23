package com.example.kotlin.chat.service

import com.example.kotlin.chat.asDomainObject
import com.example.kotlin.chat.mapToViewModel
import com.example.kotlin.chat.repository.MessageRepository
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service

@Service
@Primary
class PersistentMessageService(
    val messageRepository: MessageRepository
) : MessageService {
    override suspend fun latest(): List<MessageVM> {
        return messageRepository.findLatest()
            .mapToViewModel()
    }

    override suspend fun after(messageId: String): List<MessageVM> {
        return messageRepository.findLatest(messageId)
            .mapToViewModel()
    }

    override suspend fun post(message: MessageVM) {
        messageRepository.save(message.asDomainObject())
    }
}