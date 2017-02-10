package ar.com.adriabe.adriabedelivery.views.arrayadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ar.com.adriabe.adriabedelivery.R;
import ar.com.adriabe.adriabedelivery.model.Order;

/**
 * Created by Ale on 06/08/2015.
 */
public class OrderArrayAdapter extends ArrayAdapter<Order>{



    private final List<Order> orders;
    private final Context context;

    public OrderArrayAdapter(Context context, int resource, int textViewResourceId, List<Order> orders) {
        super(context, resource, textViewResourceId, orders);
        this.orders = orders;
        this.context = context;
    }

    public OrderArrayAdapter(Context context, List<Order> orders)  {
        super(context, R.layout.order_item, orders);
        this.orders = orders;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.order_item, parent, false);
        TextView firstLine = (TextView) rowView.findViewById(R.id.firstLine);
        TextView secondLine = (TextView) rowView.findViewById(R.id.secondLine);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        Order order = getOrders().get(position);
        String firstLineStr = null;
        if(order.getId()>0){
            String name = order.getClientName();
            firstLineStr = "N:" +String.format("%03d",order.getId()) + " " + name;
            secondLine.setText(order.getPendingBags() + " Bultos preparados.");
            // Change the icon for Windows and iPhone
            if (getOrders().get(position).getPendingBags()<10) {
                imageView.setImageResource(R.drawable.carretilla_carga);
            }
        }else{
            firstLineStr="No hay pedidos para entregar.";
            firstLine.setTextSize(16);
            secondLine.setText("");

        }
        firstLine.setText(firstLineStr);
        return rowView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders.clear();
        this.orders.addAll(orders);
    }

}
