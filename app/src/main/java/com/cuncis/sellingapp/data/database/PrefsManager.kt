package com.cuncis.sellingapp.data.database

import android.content.Context
import android.content.SharedPreferences
import hu.autsoft.krate.Krate
import hu.autsoft.krate.booleanPref
import hu.autsoft.krate.intPref
import hu.autsoft.krate.stringPref

class PrefsManager(context: Context) : Krate {

    private val PREFS_IS_LOGIN = "prefs_is_login"
    private val PREFS_USERNAME = "prefs_username"
    private val PREFS_PASSWORD = "prefs_password"
    private val PREFS_NAMA_PEGAWAI = "prefs_nama_pegawai"
    private val PREFS_JK = "prefs_jk"
    private val PREFS_ALAMAT = "prefs_alamat"
    private val PREFS_IS_AKTIF = "prefs_is_aktif"

    override val sharedPreferences: SharedPreferences = context.applicationContext.getSharedPreferences(
        "cuncis_boss_97", Context.MODE_PRIVATE
    )

    var prefsIsLogin by booleanPref(PREFS_IS_LOGIN, false)
    var prefsUsername by stringPref(PREFS_USERNAME, "")
    var prefsPassword by stringPref(PREFS_PASSWORD, "")
    var prefsNamaPegawai by stringPref(PREFS_NAMA_PEGAWAI, "")
    var prefsJk by stringPref(PREFS_JK, "")
    var prefsAlamat by stringPref(PREFS_ALAMAT, "")
    var prefsIsAktif by intPref(PREFS_IS_AKTIF, 0)

    fun logout() {
        sharedPreferences.edit().clear().apply()
    }

}