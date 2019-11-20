package com.example.evenlypoi

class HomeFeed(val meta: Meta, val response: Response)

class Meta(val code: String, val requestId: String)

class Response(val venues: List<Venue>)

class Venue (val id: String?, val name: String?)