package ru.emi.common

import com.auth0.jwk.UrlJwkProvider
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.JWTCredential
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.response.respond
import java.net.URI
import java.util.Date

private const val AZP_CLAIM = "azp"
private const val AUD_CLAIM_VALUE = "account"

fun Application.configureAuthentication() {
    val kUri = environment.config.property("keycloak.uri").getString()
    val clientId = environment.config.property("keycloak.client-id").getString()
    val kRealm = environment.config.property("keycloak.realm").getString()
    val jwkUri = "$kUri/realms/$kRealm/protocol/openid-connect/certs"
    val issuerUri = "$kUri/realms/$kRealm"

    install(Authentication) {
        jwt("auth-jwt") {
            realm = kRealm
            verifier(
                jwkProvider = UrlJwkProvider(URI.create(jwkUri).toURL()),
                issuer = issuerUri,
            ) {
                withAudience(AUD_CLAIM_VALUE)
                withClaim(AZP_CLAIM, clientId)
            }
            challenge { _, _ -> call.respond(HttpStatusCode.Unauthorized) }
            validate { validateCredential(it) }
        }
    }
}

fun validateCredential(credential: JWTCredential): JWTPrincipal? =
    when {
        credential.expiresAt
            ?.after(Date()) != true -> null

        credential.payload
            .getClaim("global_uid")
            .asString()
            .isNullOrBlank() -> null

        else -> JWTPrincipal(credential.payload)
    }