package oxxy.kero.roiaculte.team7.annotation

import io.reactivex.Scheduler

interface AppRxSchedulers {
     val io : Scheduler
     val main :Scheduler
    val computation:Scheduler
}
