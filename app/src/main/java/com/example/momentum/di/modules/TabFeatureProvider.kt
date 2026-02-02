package com.example.momentum.di.modules

import com.example.api.navigation.EditorFeatureEntry
import com.example.api.navigation.HomeFeatureEntry
import com.example.api.navigation.SettingFeatureEntry
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FeatureEntryProvider @Inject constructor(
     val editorEntry: EditorFeatureEntry,
     val settingsEntry: SettingFeatureEntry,
     val homeEntry: HomeFeatureEntry
)