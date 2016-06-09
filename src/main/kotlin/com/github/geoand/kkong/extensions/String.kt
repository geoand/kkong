package com.github.geoand.kkong.extensions

fun String.withLeadingIfMissing(lead: String): String = if(this.startsWith(lead)) this else "$lead$this"

fun String.withTrailingIfMissing(trail: String): String = if(this.endsWith(trail)) this else "$this$trail"

fun String.withBothIfMissing(input: String): String = this.withLeadingIfMissing(input).withTrailingIfMissing(input)
