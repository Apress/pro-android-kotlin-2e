binding.fab.setOnClickListener(
    view -> {
            Snackbar.make(view, 
                "Replace with your own action", 
                Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
        }
);
