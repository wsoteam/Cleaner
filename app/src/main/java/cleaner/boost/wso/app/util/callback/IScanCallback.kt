package cleaner.boost.wso.app.util.callback


import java.util.ArrayList

import cleaner.boost.wso.app.util.model.JunkInfo

interface IScanCallback {
    fun onBegin()

    fun onProgress(info: JunkInfo)

    fun onFinish(children: ArrayList<JunkInfo>)
}
