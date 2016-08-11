package com.thinkelect.thinkelect;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment;

/**
 * Created by micahherrera on 8/10/16.
 */
public class LoginAddressFragment extends Fragment implements View.OnClickListener {
    Button setAddress;
    SupportPlaceAutocompleteFragment autocompleteFragment;
    double latitude;
    double longitude;
    String address;
    SetAddress mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (SetAddress) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.address_fragment, container, false);
        setAddress = (Button)view.findViewById(R.id.button_address);
        setAddress.setOnClickListener(this);

        autocompleteFragment = (SupportPlaceAutocompleteFragment)
                getChildFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

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
        mListener.setAddress();
    }

    public interface SetAddress {
        void setAddress();
    }
}
