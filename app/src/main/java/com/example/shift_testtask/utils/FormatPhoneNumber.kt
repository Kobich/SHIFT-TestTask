package com.example.shift_testtask.utils

import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat

object FormatPhoneNumber {
    fun format(number: String, regionCode: String?): String {
        val phoneUtil = com.google.i18n.phonenumbers.PhoneNumberUtil.getInstance()
        return try {
            val parsedNumber = phoneUtil.parse(number, regionCode)
            phoneUtil.format(parsedNumber, com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL)
        } catch (e: Exception) {
            number
        }
    }
}