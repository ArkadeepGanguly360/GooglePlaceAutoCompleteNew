package com.development.googleplaceautocomplete

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var placeAdapter: PlaceArrayAdapter? = null
    private lateinit var mPlacesClient: PlacesClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        Places.initialize(this, "")
        mPlacesClient = Places.createClient(this)

        placeAdapter = PlaceArrayAdapter(this, R.layout.layout_item_places, mPlacesClient)
        autoCompleteEditText.setAdapter(placeAdapter)

        autoCompleteEditText.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            val place = parent.getItemAtPosition(position) as PlaceDataModel
            autoCompleteEditText.apply {
                setText(place.fullText)
                setSelection(autoCompleteEditText.length())
            }
        }
    }
}