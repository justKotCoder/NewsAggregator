package com.example.newsaggregator.data.rss.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
@Serializable
@SerialName("item")
data class ItemDto(
    @SerialName("title")
    @XmlElement(true)
    val title: String,

    @SerialName("link")
    @XmlElement(true)
    val link: String,

    @SerialName("description")
    @XmlElement(true)
    val description: String,

    @SerialName("category")
    @XmlElement(true)
    val categories: List<CategoryDto> = emptyList(),

    @SerialName("pubDate")
    @XmlElement(true)
    val pubDate: String,

    @SerialName("guid")
    @XmlElement(true)
    val guid: String,

    @SerialName("content:encoded")
    @XmlElement(true)
    val contents: List<ContentDto> = emptyList(),

    @XmlSerialName("creator", "http://purl.org/dc/elements/1.1/", "dc")
    @XmlElement(true)
    val dcCreator: String = "",

    @XmlSerialName("date", "http://purl.org/dc/elements/1.1/", "dc")
    @XmlElement(true)
    val dcDate: String = ""
)
