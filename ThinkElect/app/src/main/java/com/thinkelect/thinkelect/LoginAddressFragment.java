package com.thinkelect.thinkelect;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

/**
 * Created by micahherrera on 8/10/16.
 */
public class LoginAddressFragment extends Fragment implements View.OnClickListener {
    Button setAddress;
    PlaceAutocompleteFragment autocompleteFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.address_fragment, container, false);
        setAddress = (Button)view.findViewById(R.id.button_address);
        setAddress.setOnClickListener(this);
        autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                latitude = place.getLatLng().latitude;
                longitude = place.getLatLng().longitude;
                address = place.getAddress().toString();

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {

    }
}
