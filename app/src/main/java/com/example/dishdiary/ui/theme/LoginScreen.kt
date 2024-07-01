//package com.example.cinenest.ui.theme
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Surface
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.example.cinenest.R
//import com.example.cinenest.components.ButtonCompponent
//import com.example.cinenest.components.HeadingTextComponent
//import com.example.cinenest.components.MyTextFieldComponent
//import com.example.cinenest.components.NormalTextComponent
//import com.example.cinenest.components.PasswordTextFieldComponent
//
//
//@Composable
//fun com.example.cinenest.presentation.screen.LoginScreen(){
//
//    Surface(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White)
//            .padding(28.dp)
//        ) {
//
//        Column (
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center
//            ) {
//
//            NormalTextComponent(value = stringResource(id = R.string.hello))
//            HeadingTextComponent(value = stringResource(id = R.string.welcome_to_cinenest))
//            Spacer(modifier = Modifier.height(20.dp))
//            MyTextFieldComponent(
//                labelValue = stringResource(id = R.string.username_email),
//                painterResource = painterResource(id = R.drawable.usericon)
//            )
//            Spacer(modifier = Modifier.height(10.dp))
//            PasswordTextFieldComponent(
//                labelValue = stringResource(id = R.string.password),
//                painterResource = painterResource(id = R.drawable.passicon)
//            )
//            Spacer(modifier = Modifier.height(40.dp))
//            ButtonCompponent(value = stringResource(id = R.string.login) )
//
//        }
//
//    }
//}
//
//@Preview
//@Composable
//fun DefaultPreviewOfLoginScreen(){
//    com.example.cinenest.presentation.screen.LoginScreen()
//}
