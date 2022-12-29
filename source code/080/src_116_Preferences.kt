@Composable
fun TextFieldAndDescription(text:String, 
      onValueChange: (String) -> Unit = {}, 
      description: String) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        MyBasicTextField(value = text, 
            onValueChange = onValueChange)
        var helpShown by remember { mutableStateOf(false) }
        val icon = createRef()
        Icon(Icons.TwoTone.Help, contentDescription = "", 
            modifier = Modifier
		        .constrainAs(icon) {
		            top.linkTo(parent.top, margin = -6.dp)
		            end.linkTo(parent.end, margin = 5.dp)
		        }
		        .height(33.dp)
		        .width(33.dp)
		        .clickable { helpShown = true })
        HelpDialog(text = description, shown = helpShown, 
            onDismiss = { helpShown = false })
    }
}

@Composable
fun HelpDialog(text:String, shown:Boolean, 
      onDismiss:()->Unit) {
    if(shown) {
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {},
            dismissButton = {},
            text = {
                Text(text = text, fontSize = 17.sp)
            },
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(10.dp),
            shape = RoundedCornerShape(5.dp),
            backgroundColor = Color.White,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        )
    }
}

@Composable
fun MyBasicTextField(
    value: String,
    singleline: Boolean = true,
    onValueChange: (String) -> Unit = {},
    placeholder: String = "",
    keyboardOptions: KeyboardOptions = 
        KeyboardOptions.Default,
    keyboardActions: KeyboardActions = 
        KeyboardActions.Default,
    onFocusLost: ()->Unit  = {},
    modifier: Modifier = Modifier
){
    BasicTextField(value = value,
        onValueChange = onValueChange,
        singleLine = singleline,
        textStyle = TextStyle.Default.merge(
            TextStyle(fontSize = 18.sp)),
        modifier = modifier
            .padding(horizontal = 5.dp)
            .fillMaxWidth()
            .onFocusChanged { fs->
                if(!fs.isFocused) onFocusLost() },
        decorationBox = { innerTextField ->
            Box(modifier = Modifier
                  .fillMaxWidth()
                  .offset(x = 2.dp),
                contentAlignment = Alignment.CenterStart,
            ) {
                if (value.isEmpty()) {
                    Text(placeholder, fontSize = 18.sp, 
                         color = Color.Gray)
                }
                innerTextField()
            }
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions)
}
