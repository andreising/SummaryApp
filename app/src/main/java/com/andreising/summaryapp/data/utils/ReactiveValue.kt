package com.andreising.summaryapp.data.utils

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class ReactiveValue<T : Any>(initial: T) {

    private val subject = BehaviorSubject.createDefault(initial)

    fun get(): T = subject.value ?: throw IllegalStateException("Value not initialized")

    fun update(value: T) {
        subject.onNext(value)
    }

    fun observe(): Observable<T> = subject.hide()
}