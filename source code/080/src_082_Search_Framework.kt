@Composable
fun MySearchWidget() {
    var searchTxt by remember { mutableStateOf("") }
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        factory = { context ->
            // This instantiates a non-Compose 
            // View:
            val searchManager = 
                getSystemService(SEARCH_SERVICE)
                    as SearchManager
            SearchView(context).apply {
                setSearchableInfo(
                    searchManager.
                        getSearchableInfo(
                            componentName))
                isIconifiedByDefault = false
                setQuery(searchTxt, false)
            }
        },
        update = { view ->
            // View's been inflated or state has 
            // been updated. Add logic here if 
            // necessary.
            view.setQuery(searchTxt, false)
        }
    )
}
