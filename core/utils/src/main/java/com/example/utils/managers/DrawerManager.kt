/**
 * @author Amit Kumar on 27/12/25
 */

package com.example.utils.managers

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.utils.managers.DrawerManager.Companion.drawerSaver
import kotlinx.coroutines.flow.MutableStateFlow

interface DrawerManager {
    suspend fun closeDrawer()
    suspend fun openDrawer()
    val drawerValue: State<DrawerValue>
    val selectedItem: MutableStateFlow<Int>


    class Base  (
        private val drawerState: DrawerState
    ) : DrawerManager {
        override val drawerValue: State<DrawerValue> = derivedStateOf { drawerState.currentValue }
        override val selectedItem: MutableStateFlow<Int> = MutableStateFlow(0)

        override suspend fun openDrawer() {
            drawerState.open()
        }

        override suspend fun closeDrawer() {
            drawerState.close()
        }

    }

    companion object{
        fun drawerSaver(drawerState: DrawerState) = object :Saver<DrawerManager, Any>{
            override fun SaverScope.save(value: DrawerManager): Any? {
                return null
            }

            override fun restore(value: Any): DrawerManager? {
                return Base(drawerState = drawerState)
            }

        }
    }
}

val LocalDrawerManager = staticCompositionLocalOf<DrawerManager>{
    error("Please Provide Drawer Manager")
}


@Composable
fun rememberDrawerManager(drawerState: DrawerState) : DrawerManager = rememberSaveable(saver = drawerSaver(drawerState)){
    DrawerManager.Base(drawerState)
}
interface DrawerItem{
    val drawerTitle : String
        @Composable get
    val drawerIcon : Int
        @Composable get
}