package com.example.evenlypoi

class HomeFeed(val meta: Meta, val response: Response)

class Meta(val code: String, val requestId: String)

class Response(val venues: List<Venue>)

class Venue (
    val id: String,
    val name: String,
    val location: Location,
    val categories: List<Category>?)

class Location (
    val address: String,
    val distance: String)

class Category(
    val shortName: String,
    val icon: Icon)

class Icon(
    val prefix: String,
    val suffix: String)
