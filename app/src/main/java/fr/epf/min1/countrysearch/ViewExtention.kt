package fr.epf.min1.countrysearch

import android.util.Log
import android.view.View

private const val TAG = "ViewExtension"
fun View.click(action: (View)->Unit){
    Log.d(TAG, "click !!!")
    this.setOnClickListener(action)
}