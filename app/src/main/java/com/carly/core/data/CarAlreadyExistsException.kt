package com.carly.core.data

class CarAlreadyExistsException : Exception() {
    override val message: String
        get() = "Car already exists"
}