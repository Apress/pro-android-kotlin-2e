  val uri = Contacts.getLookupUri(id, lookupKey)
  val intent = Intent(Intent.ACTION_EDIT)
  // the following must be done in _one_ call, do not
  // chain .setData() and .setType(), because they
  // overwrite each other!
  intent.setDataAndType(uri, Contacts.CONTENT_ITEM_TYPE)
  intent.putExtra("finishActivityOnSaveCompleted", true)

  // now put any data to update, for example 
  intent.putExtra(Intents.Insert.EMAIL, newEmail)
  ...
  startActivity(intent)
