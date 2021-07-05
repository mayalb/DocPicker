package com.example.docpicker
import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

/**
 * SharedPreferences manager. Provides all methods to get/save values in SharedPreferences
 * Usage:
 * PrefUtils.with(context).save(PrefUtils.Keys.key, value)
 * val int = PrefUtils.with(context).getInt(PrefUtils.Keys.key, defValue)
 */
class PrefUtil{

    // enum to store keys
    enum class Keys {
        KEY1,
        ID,
        token,
        idModel,
        taskUuid,

        dark,
        idUser,

        nameAgent,
        NOTIFICATION_COUNT

    }

    companion object {
        private var singleton: PrefUtil? = null
        private lateinit var preferences: SharedPreferences
        private lateinit var editor: SharedPreferences.Editor

        fun with(context: Context): PrefUtil {
            if (null == singleton)
                singleton = Builder(context, null, -1).build()
            return singleton as PrefUtil
        }

        fun with(context: Context, name: String, mode: Int): PrefUtil {
            if (null == singleton)
                singleton = Builder(context, name, mode).build()
            return singleton as PrefUtil
        }

    }

    constructor()

    @SuppressLint("CommitPrefEdits")
    constructor(context: Context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context)
        editor = preferences.edit()
    }

    @SuppressLint("CommitPrefEdits")
    constructor(context: Context, name: String, mode: Int) {
        preferences = context.getSharedPreferences(name, mode)
        editor = preferences.edit()
    }

    fun save(key: Keys, value: Boolean) {
        editor.putBoolean(key.name, value).apply()
    }

    fun save(key: Keys, value: Float) {
        editor.putFloat(key.name, value).apply()
    }

    fun save(key: Keys, value: Int) {
        editor.putInt(key.name, value).apply()
    }

    fun save(key: Keys, value: Long) {
        editor.putLong(key.name, value).apply()
    }

    fun save(key: Keys, value: String) {
        editor.putString(key.name, value).apply()
    }

    fun save(key: Keys, value: Set<String>) {
        editor.putStringSet(key.name, value).apply()
    }

    fun getBoolean(key: Keys, defValue: Boolean): Boolean {
        return preferences.getBoolean(key.name, defValue)
    }

    fun getFloat(key: Keys, defValue: Float): Float {
        return try {
            preferences.getFloat(key.name, defValue)
        } catch (ex: ClassCastException) {
            preferences.getString(key.name, defValue.toString())!!.toFloat()
        }
    }

    fun getInt(key: Keys, defValue: Int): Int {
        return try {
            preferences.getInt(key.name, defValue)
        } catch (ex: ClassCastException) {
            preferences.getString(key.name, defValue.toString())!!.toInt()
        }
    }

    fun getLong(key: Keys, defValue: Long): Long {
        return try {
            preferences.getLong(key.name, defValue)
        } catch (ex: ClassCastException) {
            preferences.getString(key.name, defValue.toString())!!.toLong()
        }
    }

    fun getString(key: Keys, defValue: String): String? {
        return preferences.getString(key.name, defValue)
    }

    fun getStringSet(key: Keys, defValue: Set<String>): Set<String>? {
        return preferences.getStringSet(key.name, defValue)
    }

    fun getAll(): MutableMap<String, *>? {
        return preferences.all
    }

    fun remove(key: String) {
        editor.remove(key).apply()
    }

    fun clear() {
        editor.clear().apply()
    }

    private class Builder(val context: Context, val name: String?, val mode: Int) {

        fun build(): PrefUtil {
            if (mode == -1 || name == null) {
                return PrefUtil(context)
            }
            return PrefUtil(context, name, mode)
        }
    }

}