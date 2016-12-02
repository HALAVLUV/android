package com.ohelshem.app.android.login

import android.app.Activity
import com.afollestad.materialdialogs.GravityEnum
import com.afollestad.materialdialogs.MaterialDialog
import com.ohelshem.app.android.stringArrayRes
import com.ohelshem.app.controller.storage.TeacherStorage
import com.yoavst.changesystemohelshem.R


object PrimaryClassDialog {
    fun create(storage: TeacherStorage, activity: Activity, listener: () -> Unit): MaterialDialog {
        val classes = storage.classes

        val layers = activity.stringArrayRes(R.array.layers)

        return MaterialDialog.Builder(activity)
                .title(R.string.set_primary_class)
                .autoDismiss(true)
                .canceledOnTouchOutside(false)
                .cancelable(false)
                .itemsGravity(GravityEnum.CENTER)
                .items(listOf(activity.getString(R.string.no_primary_class)) + classes.map { "${layers[it.layer - 9]}'${it.clazz}" })
                .itemsCallbackSingleChoice(0) { dialog, view, which, text ->
                    if (which == 0)
                        storage.primaryClass = null
                    else
                        storage.primaryClass = classes[which - 1]

                    listener()
                    true
                }
                .positiveText(R.string.accept)
                .show()
    }
}