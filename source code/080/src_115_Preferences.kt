package com.example.myapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Help
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

import com.example.myapp.vm.SettingsViewModel

class SettingsScreen(private val vm: SettingsViewModel) {

    @Composable
    fun make() {
        val ctx = LocalContext.current
        val DROPDOWN_TITLES: Array<String> = 
            arrayOf("Item 1", "Item 2") 
        
        var selectMenu by 
            remember { mutableStateOf(false) }
        Box(modifier = Modifier.verticalScroll(
              rememberScrollState())) {
            Column {
                // ----------------------------------------
                SectionHeader("Header 1")
                // ----------------------------------------
                Label("Some String")
                TextFieldAndDescription(
                      text = vm.someString, 
                      onValueChange = {
                        vm.someString = it }, 
                      description = "Description")
                ...
                Label("Some String")
                Box {
                    ButtonForDropDown(
                        DROPDOWN_TITLES[vm.selector],
                        { selectMenu = !selectMenu },
                        description = "Description"
                    )
                    DropdownMenu(expanded = selectMenu,
                        onDismissRequest = { 
                            selectMenu = false }) {
                        DROPDOWN_TITLES.forEachIndexed { 
                            i, s ->
                            DropdownMenuItem(onClick = {
                                vm.selector = i
                                selectMenu = false
                            }) {
                                Text(s)
                            }
                        }
                    }
                }
                // ----------------------------------------
                SectionHeader( ... )
                // ----------------------------------------
                ...
            }
        }
    }

    ///////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////

    @Composable
    private fun Label(text:String) {
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = text)
    }

    @Composable
    private fun SectionHeader(text:String) {
        Spacer(modifier = Modifier.height(25.dp))
        Text(text = text, fontSize = 20.sp, 
             fontWeight = FontWeight.Bold)
    }

    @Composable
    private fun 
    ButtonForDropDown(text:String, onClick:()->Unit, 
          description:String="") {
        ConstraintLayout(modifier = 
              Modifier.fillMaxWidth()) {
            var helpShown by 
                remember { mutableStateOf(false) }
            val icon = createRef()
            Button( colors = ButtonDefaults.
                  buttonColors(
                      backgroundColor = 
                          Color(200,200,255)),
                modifier = Modifier.fillMaxWidth(),
                onClick = onClick
            ) {
                Text(text = text)
            }
            Icon(Icons.TwoTone.Help, 
                contentDescription = "", 
                modifier = Modifier
                  .constrainAs(icon) {
                    top.linkTo(parent.top, margin = 5.dp)
                    end.linkTo(parent.end, margin = 5.dp)
                  }
                  .height(35.dp)
                  .width(35.dp)
                  .clickable { helpShown = true })
            HelpDialog(text = description, 
                shown = helpShown, 
                onDismiss = { helpShown = false })
        }
    }
}
