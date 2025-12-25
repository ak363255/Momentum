/**
 * @author Amit Kumar on 25/12/25
 */

package com.example.utils.platform.viemodel.contract

import androidx.lifecycle.Lifecycle
import com.example.utils.platform.viemodel.work.WorkScope

interface Actor<S,E,A,F>{
    fun WorkScope<S,A,F>.handleEvent(event : E)
}