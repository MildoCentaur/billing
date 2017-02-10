package ar.com.adriabe.adriabedelivery.views.arrayadapter;

import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ar.com.adriabe.adriabedelivery.R;
import ar.com.adriabe.adriabedelivery.model.OrderItemDetail;

public class PackagesArrayAdapter extends ArrayAdapter<OrderItemDetail> {

	private List<OrderItemDetail> values;
	private Context context; 
	public PackagesArrayAdapter(Context context,List<OrderItemDetail> values) {
		super(context, R.layout.item_detail_item, values);
		this.values = values;
		
		this.context = context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.item_detail_item, parent, false);
		TextView firstLine = (TextView) rowView.findViewById(R.id.detailLine);
		ImageView icon = (ImageView) rowView.findViewById(R.id.detailIcon);
		OrderItemDetail item = getValues().get(position);
		
		if(item.isDelivered()){
			icon.setImageResource(R.drawable.dialog_ok_color);
		}
		String line = String.format(new Locale("es", "ES"), "%1$,.2f Kg", item.getWeight());
		
		firstLine.setText(line);
		
		 

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

	public List<OrderItemDetail> getValues() {
		return values;
	}

	public void setValues(List<OrderItemDetail> values) {
		this.values = values;
	}
}