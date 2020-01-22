package com.alexklab.ksharedpreferences

import android.content.SharedPreferences
import androidx.annotation.MainThread
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * Class for implementation LiveData behavior with SharedPreference
 */
class LivePreference<T : Any>(
    val key: String,
    val defaultValue: T,
    private val prefs: Lazy<SharedPreferences>
) {

    var value: T
        get() = delegate.value ?: defaultValue
        set(newValue) {
            delegate.value = value
            prefs.value.edit { putValue(key, newValue) }
        }

    private val delegate = MutableLiveData<T>()

    private val onPreferenceChangeListener =
        SharedPreferences.OnSharedPreferenceChangeListener { _, preferenceKey ->
            if (key == preferenceKey) {
                setLivePreferenceValue()
            }
        }

    init {
        setLivePreferenceValue()
        registerOnSharedPreferenceChangeListener()
    }

    fun registerOnSharedPreferenceChangeListener() {
        prefs.value.registerOnSharedPreferenceChangeListener(onPreferenceChangeListener)
    }

    fun unregisterOnSharedPreferenceChangeListener() {
        prefs.value.unregisterOnSharedPreferenceChangeListener(onPreferenceChangeListener)
    }

    /**
     * Adds the given observer to the observers list within the lifespan of the given
     * owner. The events are dispatched on the main thread. If LiveData already has data
     * set, it will be delivered to the observer.
     * <p>
     * The observer will only receive events if the owner is in {@link Lifecycle.State#STARTED}
     * or {@link Lifecycle.State#RESUMED} state (active).
     * <p>
     * If the owner moves to the {@link Lifecycle.State#DESTROYED} state, the observer will
     * automatically be removed.
     * <p>
     * When data changes while the {@code owner} is not active, it will not receive any updates.
     * If it becomes active again, it will receive the last available data automatically.
     * <p>
     * LiveData keeps a strong reference to the observer and the owner as long as the
     * given LifecycleOwner is not destroyed. When it is destroyed, LiveData removes references to
     * the observer &amp; the owner.
     * <p>
     * If the given owner is already in {@link Lifecycle.State#DESTROYED} state, LiveData
     * ignores the call.
     * <p>
     * If the given owner, observer tuple is already in the list, the call is ignored.
     * If the observer is already in the list with another owner, LiveData throws an
     * {@link IllegalArgumentException}.
     *
     * @param owner    The LifecycleOwner which controls the observer
     * @param observer The observer that will receive the events
     */
    @MainThread
    fun observe(owner: LifecycleOwner, observer: Observer<in T>) = delegate.observe(owner, observer)

    /**
     * Adds the given observer to the observers list within the lifespan of the given
     * owner. The events are dispatched on the main thread. If LiveData already has data
     * set, it will be delivered to the observer.
     * <p>
     * The observer will only receive events if the owner is in {@link Lifecycle.State#STARTED}
     * or {@link Lifecycle.State#RESUMED} state (active).
     * <p>
     * If the owner moves to the {@link Lifecycle.State#DESTROYED} state, the observer will
     * automatically be removed.
     * <p>
     * When data changes while the {@code owner} is not active, it will not receive any updates.
     * If it becomes active again, it will receive the last available data automatically.
     * <p>
     * LiveData keeps a strong reference to the observer and the owner as long as the
     * given LifecycleOwner is not destroyed. When it is destroyed, LiveData removes references to
     * the observer &amp; the owner.
     * <p>
     * If the given owner is already in {@link Lifecycle.State#DESTROYED} state, LiveData
     * ignores the call.
     * <p>
     * If the given owner, observer tuple is already in the list, the call is ignored.
     * If the observer is already in the list with another owner, LiveData throws an
     * {@link IllegalArgumentException}.
     *
     * @param owner    The LifecycleOwner which controls the observer
     * @param observer The observer that will receive the events
     */
    @MainThread
    fun observe(owner: () -> Lifecycle, observer: (T) -> Unit) = delegate.observe(owner, observer)

    /**
     * Adds the given observer to the observers list. This call is similar to
     * {@link LiveData#observe(LifecycleOwner, Observer)} with a LifecycleOwner, which
     * is always active. This means that the given observer will receive all events and will never
     * be automatically removed. You should manually call {@link #removeObserver(Observer)} to stop
     * observing this LiveData.
     * While LiveData has one of such observers, it will be considered
     * as active.
     * <p>
     * If the observer was already added with an owner to this LiveData, LiveData throws an
     * {@link IllegalArgumentException}.
     *
     * @param observer The observer that will receive the events
     */
    @MainThread
    fun observeForever(observer: Observer<in T>) = delegate.observeForever(observer)

    /**
     * Adds the given observer to the observers list. This call is similar to
     * {@link LiveData#observe(LifecycleOwner, Observer)} with a LifecycleOwner, which
     * is always active. This means that the given observer will receive all events and will never
     * be automatically removed. You should manually call {@link #removeObserver(Observer)} to stop
     * observing this LiveData.
     * While LiveData has one of such observers, it will be considered
     * as active.
     * <p>
     * If the observer was already added with an owner to this LiveData, LiveData throws an
     * {@link IllegalArgumentException}.
     *
     * @param observer The observer that will receive the events
     */
    @MainThread
    fun observeForever(observer: (T) -> Unit) = delegate.observeForever(observer)

    /**
     * Removes the given observer from the observers list.
     *
     * @param observer The Observer to receive events.
     */
    @MainThread
    fun removeObserver(observer: Observer<in T>) = delegate.removeObserver(observer)

    /**
     * Removes the given observer from the observers list.
     *
     * @param observer The Observer to receive events.
     */
    @MainThread
    fun removeObserver(observer: (T) -> Unit) = delegate.removeObserver(observer)

    /**
     * Removes all observers that are tied to the given {@link LifecycleOwner}.
     *
     * @param owner The {@code LifecycleOwner} scope for the observers to be removed.
     */
    @MainThread
    fun removeObservers(owner: LifecycleOwner) = delegate.removeObservers(owner)

    /**
     * Removes all observers that are tied to the given {@link LifecycleOwner}.
     *
     * @param owner The {@code LifecycleOwner} scope for the observers to be removed.
     */
    @MainThread
    fun removeObservers(owner: () -> Lifecycle) = delegate.removeObservers(owner)

    /**
     * Returns true if this LiveData has observers.
     *
     * @return true if this LiveData has observers
     */
    fun hasObservers(): Boolean = delegate.hasObservers()

    /**
     * Returns true if this LiveData has active observers.
     *
     * @return true if this LiveData has active observers
     */
    fun hasActiveObservers(): Boolean = delegate.hasActiveObservers()

    private fun setLivePreferenceValue() {
        val value = prefs.value.getValue(key, defaultValue)
        if (value != delegate.value) {
            delegate.value = value
        }
    }

}
