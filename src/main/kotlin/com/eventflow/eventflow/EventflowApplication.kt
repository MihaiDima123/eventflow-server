package com.eventflow.eventflow

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EventflowApplication

fun main(args: Array<String>) {
	runApplication<EventflowApplication>(*args)
}
