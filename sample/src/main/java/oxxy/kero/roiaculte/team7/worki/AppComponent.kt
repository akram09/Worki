package oxxy.kero.roiaculte.team7.worki

import dagger.Component
import dagger.Module
import worki.android.WorkiModule

@Component(modules =  [AppModule::class])
interface AppComponent {

}
@Module(includes = [WorkiModule::class])
abstract  class AppModule{

}