package ar.com.adriabe.adriabedelivery.views.fragments;

/**
 * Created by Ale on 14/08/2015.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import ar.com.adriabe.adriabedelivery.MainActivity;
import ar.com.adriabe.adriabedelivery.R;
import ar.com.adriabe.adriabedelivery.model.Order;
import ar.com.adriabe.adriabedelivery.services.OrderReporter;
import ar.com.adriabe.adriabedelivery.views.arrayadapter.OrderArrayAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public abstract class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    protected static final String ARG_SECTION_TITLE  = "section_title";

    public static final String ORDER_SELECTED = "ar.com.kendo.deliverycheckpoint.ORDERSELECTED";
    private static final String ARG_ORDER_SELECTED  = "section_number";
    private static OrderReporter reporter;

    public PlaceholderFragment() {
    }
    public PlaceholderFragment initializePlaceholderFragment() {
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_TITLE,generateScreenTitle());
        setArguments(args);
        return this;
    }
    public abstract String generateScreenTitle();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getString(ARG_SECTION_TITLE));

    }
}
