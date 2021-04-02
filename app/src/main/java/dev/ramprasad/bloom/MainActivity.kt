/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.ramprasad.bloom

import dev.ramprasad.bloom.ui.Home
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import dev.ramprasad.bloom.ui.LandingScreen
import dev.ramprasad.bloom.ui.Login
import dev.ramprasad.bloom.ui.Welcome
import dev.ramprasad.bloom.ui.theme.MyTheme
import androidx.navigation.compose.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                val navController = rememberNavController()
                NavHost(navController,"LandingScreen"){
                    composable("LandingScreen"){
                        LandingScreen {
                            navController.navigate("Welcome"){
                                popUpTo("LandingScreen") {
                                    inclusive = true
                                }
                            }
                        }
                    }
                    composable("Welcome"){
                        Welcome(){
                            navController.navigate("Login")
                        }
                    }
                    composable("Login"){ Login() }
                    composable("Home"){ Home() }
                }
            }
        }
    }
}


/*@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}*/
