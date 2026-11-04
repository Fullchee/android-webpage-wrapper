package com.fullchee.webwrapper

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class AllowlistTest {
    @Test
    fun allowsEveryHardcodedSite() {
        SITES.forEach { assertTrue(it.url, isAllowed(it.url)) }
    }

    @Test
    fun allowsPathsOnAllowedSite() {
        assertTrue(isAllowed("https://fullchee-dashboard.pages.dev/some/page?x=1"))
    }

    @Test
    fun allowsCloudflareDashboard() {
        assertTrue(isAllowed("https://dash.cloudflare.com/some/account?x=1"))
    }

    @Test
    fun allowsAllCloudflareSubdomains() {
        assertTrue(isAllowed("https://www.cloudflare.com/"))
        assertTrue(isAllowed("https://api.cloudflare.com/client/v4"))
    }

    @Test
    fun allowsGoogleSignIn() {
        assertTrue(isAllowed("https://accounts.google.com/o/oauth2/auth?client_id=abc"))
    }

    @Test
    fun allowsCloudflareAccessSubdomain() {
        assertTrue(isAllowed("https://myteam.cloudflareaccess.com/cdn-cgi/access/login"))
    }

    @Test
    fun allowsCloudflareChallenge() {
        assertTrue(isAllowed("https://challenges.cloudflare.com/turnstile/v0/api.js"))
    }

    @Test
    fun blocksUnlistedSite() {
        assertFalse(isAllowed("https://www.wikipedia.org/"))
    }

    @Test
    fun blocksLookalikeDomain() {
        assertFalse(isAllowed("https://evilaccounts.google.com.attacker.net/"))
        assertFalse(isAllowed("https://notcloudflareaccess.com/"))
        assertFalse(isAllowed("https://notcloudflare.com/"))
    }

    @Test
    fun blocksSearchOnGoogleDotCom() {
        assertFalse(isAllowed("https://www.google.com/search?q=anything"))
    }

    @Test
    fun blocksPlainHttp() {
        assertFalse(isAllowed("http://fullchee-dashboard.pages.dev/"))
    }

    @Test
    fun blocksNonWebSchemes() {
        assertFalse(isAllowed("intent://scan/#Intent;scheme=zxing;end"))
        assertFalse(isAllowed("mailto:a@b.com"))
    }

    @Test
    fun blocksMalformedUrl() {
        assertFalse(isAllowed("https://exa mple.com"))
    }
}
