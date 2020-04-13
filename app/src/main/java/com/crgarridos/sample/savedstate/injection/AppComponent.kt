package com.crgarridos.sample.savedstate.injection

import com.crgarridos.sample.savedstate.DemoApp
import com.crgarridos.sample.savedstate.ui.main.injection.ActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityModule::class
])
interface AppComponent: AndroidInjector<DemoApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: DemoApp): Builder
        fun build(): AppComponent
    }
    override fun inject(application: DemoApp)
}