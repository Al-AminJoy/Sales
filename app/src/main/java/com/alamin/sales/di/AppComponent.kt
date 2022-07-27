package com.alamin.sales.di;

import android.content.Context
import androidx.lifecycle.ViewModel
import com.alamin.sales.view.activity.MainActivity
import com.alamin.sales.view.fragment.AttendanceFragment
import com.alamin.sales.view.fragment.HomeFragment
import dagger.BindsInstance
import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = [NetworkModule::class,ViewModelModule::class])
 interface AppComponent {
    fun injectMain (mainActivity: MainActivity)
    fun injectAttendance(attendanceFragment: AttendanceFragment)
    fun injectHome(homeFragment: HomeFragment)

    fun getViewModelMap(): Map<Class<*>, ViewModel>

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): AppComponent
    }
}
