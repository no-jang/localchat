package de.localchat.discovery

import kotlinx.coroutines.flow.Flow

interface DiscoveryBackend : AutoCloseable {
    fun send(discovery: ClientDiscovery)
    fun discovered(): Flow<ClientDiscovery>
}