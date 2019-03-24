# KSharedPreferences
Android library that allow you to use Android SharedPreferences in elegant way, like LiveData or KProperty

Installation
-----
In your `project/build.gradle` file add a dependency
```groovy
allprojects {
    repositories {
	// ...
	maven { url 'https://jitpack.io' }
    }
}
```
In your `app/build.gradle` file add a dependency
```groovy
dependencies {
    implementation "com.github.alexklab:KSharedPreferences:$ksharedpreferences_version"
}
```
Usage
-----
#### SharedPreference properties 
```kotlin
object Prefs {
    var isNotificationsEnabled: Boolean by SharedPreferenceProperty(defaultValue = false) 
}

class EditPrefsFragment: Fragment(){

    // Get preference value
    val isNotificationsEnabled = Prefs.isNotificationsEnabled
  
    // ...
  
    // Set preference value 
    Prefs.isNotificationsEnabled = isEnabled
      
}
```

#### LivePreference properties
```kotlin
object Prefs {
    val darkThemeTrigger by LivePreferenceProperty(defaultValue = false)
}

class FragmentA: Fragment() {
    
    private fun onPreferenceChanged(isEnabled: Boolean) {
        // Set preference value
        Prefs.darkThemeTrigger.value = isEnabled
    }
}

class FragmentB: Fragment() {
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Observe preference changes
        Prefs.darkThemeTrigger.observe(this, Observer {
            // ... do your stuffs
        })
    }
}
