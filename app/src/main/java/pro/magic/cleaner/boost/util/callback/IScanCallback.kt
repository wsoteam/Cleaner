package pro.magic.cleaner.boost.util.callback


import java.util.ArrayList

import pro.magic.cleaner.boost.util.model.JunkInfo

interface IScanCallback {
    fun onBegin()

    fun onProgress(info: JunkInfo)

    fun onFinish(children: ArrayList<JunkInfo>)
}
