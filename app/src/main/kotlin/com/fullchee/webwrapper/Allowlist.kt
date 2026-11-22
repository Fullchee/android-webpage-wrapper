package com.fullchee.webwrapper

import java.net.URI

private val SIGN_IN_DOMAINS = listOf(
    "accounts.google.com",
    "accounts.youtube.com",
    "cloudflareaccess.com",
    "cloudflare.com",
)

private val ALLOWED_DOMAINS = listOf(URI(BuildConfig.HOME_URL).host) + SIGN_IN_DOMAINS

/** A host matches a domain exactly or as a subdomain, so `evil-google.com` never matches `google.com`. */
fun isAllowed(url: String): Boolean {
    val uri = runCatching { URI(url) }.getOrNull() ?: return false
    if (uri.scheme != "https") return false
    val host = uri.host?.lowercase() ?: return false
    return ALLOWED_DOMAINS.any { host == it || host.endsWith(".$it") }
}
