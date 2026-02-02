import com.example.module_injector.navigation.Navigable
import kotlinx.serialization.Serializable

sealed class FeatureRootRoute : Navigable{
    @Serializable
    object HomeRootRoute : FeatureRootRoute()
    @Serializable
    object SettingRootRoute : FeatureRootRoute()
    @Serializable
    object EditorRootRoute:FeatureRootRoute()
}