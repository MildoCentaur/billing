package ar.com.adriabe.adriabedelivery.views.arrayadapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import ar.com.adriabe.adriabedelivery.model.OrderItem;

public class OrderItemArrayAdapter extends ArrayAdapter<OrderItem>{

	private List<OrderItem> values;
	private Context context; 
	public OrderItemArrayAdapter(Context context,List<OrderItem> values) {
		super(context, android.R.layout.simple_list_item_2, values);
		this.values = values;
		
		this.context = context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(android.R.layout.simple_list_item_2, parent, false);
		TextView firstLine = (TextView) rowView.findViewById(android.R.id.text1);
		TextView secondLine = (TextView) rowView.findViewById(android.R.id.text2);
		 
		firstLine.setText(getValues().get(position).orderItemDeliveryLabel());
		secondLine.setText(getValues().get(position).orderItemDeliveryRatio());
		 

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

	public List<OrderItem> getValues() {
		return values;
	}

	public void setValues(List<OrderItem> values) {
		this.values = values;
	}
}