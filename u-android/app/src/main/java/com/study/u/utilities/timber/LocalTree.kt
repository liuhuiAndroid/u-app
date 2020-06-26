package com.study.u.utilities.timber

import com.study.u.AppContext
import com.study.u.utilities.LOG_PATH
import com.study.u.utilities.TimeUtils
import okio.Okio
import timber.log.Timber
import java.io.File
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.*

/**
 * 保存日志到本地目录
 */
class LocalTree : Timber.DebugTree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        super.log(priority, tag, message, t)
        saveNormalLog(tag, message)
    }

    /**
     * 保存日志
     */
    private fun saveNormalLog(tag: String?, message: String) {
        val fileName = "vr_log_" + SimpleDateFormat("yyyyMMdd").format(Date()) + ".txt"
        val saveFilePath = AppContext.externalCacheDir?.absolutePath.toString() + LOG_PATH
        val logDir = File(saveFilePath)
        if (!logDir.exists()) {
            logDir.mkdirs()
        }
        val file = File(saveFilePath, fileName)
        writeString(file, "[${TimeUtils.getNowString()}][$tag]$message")
    }

    /**
     * 使用 Okio 写文件
     */
    private fun writeString(file: File, content: String) {
        Okio.appendingSink(file).use { fileSink ->
            Okio.buffer(fileSink).use { bufferedSink ->
                // 奇怪为什么格式是GBK的
                bufferedSink.writeString(content, Charset.forName("GBK"))
                // bufferedSink.writeUtf8(content)
                bufferedSink.writeUtf8("\n")
            }
        }
    }
}