package ar.com.adriabe.adriabedelivery.views.fragments;

/**
 * Created by Ale on 14/08/2015.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import ar.com.adriabe.adriabedelivery.ListPackagesActivity;
import ar.com.adriabe.adriabedelivery.MainActivity;
import ar.com.adriabe.adriabedelivery.R;
import ar.com.adriabe.adriabedelivery.model.Order;
import ar.com.adriabe.adriabedelivery.services.OrderReporter;
import ar.com.adriabe.adriabedelivery.views.arrayadapter.OrderArrayAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class ListOrdersPlaceholderFragment extends PlaceholderFragment  {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ORDER_SELECTED = "ar.com.adriabe.adriabedelivery.ORDERSELECTED";
    public static final String PEDIDOS_A_ENTREGAR = "Pedidos a Entregar";
    private static OrderReporter reporter;
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */


    public ListOrdersPlaceholderFragment() {
        reporter = new OrderReporter();
        initializePlaceholderFragment();
    }

    @Override
    public String generateScreenTitle() {
        return PEDIDOS_A_ENTREGAR;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ListView rootView = (ListView) inflater.inflate(R.layout.fragment_main, container, false);
        rootView.setAdapter(new OrderArrayAdapter(
                container.getContext(),
                reporter.listPendingOrders()));

            rootView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Order order = (Order) parent.getAdapter().getItem(position);
                    ((ListView) parent).setItemChecked(position, true);
                    if (order!= null && order.getId() > 0) {
                        FragmentManager fragmentManager = getParentFragment().getFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.container, new ListPackagesPlaceholderFragment(order))
                                .commit();
                    }
                }
            });
        return rootView;
    }


}
