/**
 * @author Amit Kumar on 01/05/26
 */

package com.example.impl.domain.entity

import com.example.utils.functional.DomainFailures
import java.util.Date

internal sealed class EditorFailures : DomainFailures{
    data class TimeOverlayError(val startOverlay : Date?,val endOverlay : Date?): EditorFailures()
    data class OtherError(val throwable : Throwable?): EditorFailures()
}