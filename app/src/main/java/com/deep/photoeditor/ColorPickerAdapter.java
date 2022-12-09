package com.deep.photoeditor;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ColorPickerAdapter extends RecyclerView.Adapter<ColorPickerAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Integer> colorPickerColors;
    private OnColorPickerClickListener onColorPickerClickListener;

    ColorPickerAdapter(@NonNull Context context, @NonNull List<Integer> colorPickerColors) {
        this.inflater = LayoutInflater.from(context);
        this.colorPickerColors = colorPickerColors;
    }

    ColorPickerAdapter(@NonNull Context context) {
        this(context, getDefaultColors(context));
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.color_picker_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.colorPickerView.setBackgroundColor(colorPickerColors.get(position));
    }

    @Override
    public int getItemCount() {
        return colorPickerColors.size();
    }

    public void setOnColorPickerClickListener(OnColorPickerClickListener onColorPickerClickListener) {
        this.onColorPickerClickListener = onColorPickerClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View colorPickerView;

        public ViewHolder(View itemView) {
            super(itemView);
            colorPickerView = itemView.findViewById(R.id.color_picker_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onColorPickerClickListener != null)
                        onColorPickerClickListener.onColorPickerClickListener(colorPickerColors.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnColorPickerClickListener {
        void onColorPickerClickListener(int colorCode);
    }

    public static List<Integer> getDefaultColors(Context context) {
        ArrayList<Integer> colorPickerColors = new ArrayList<>();
        colorPickerColors.add(ContextCompat.getColor(context, R.color.blue_color_picker));
        colorPickerColors.add(ContextCompat.getColor(context, R.color.brown_color_picker));
        colorPickerColors.add(ContextCompat.getColor(context, R.color.green_color_picker));
        colorPickerColors.add(ContextCompat.getColor(context, R.color.orange_color_picker));
        colorPickerColors.add(ContextCompat.getColor(context, R.color.red_color_picker));
        colorPickerColors.add(ContextCompat.getColor(context, R.color.black));
        colorPickerColors.add(ContextCompat.getColor(context, R.color.red_orange_color_picker));
        colorPickerColors.add(ContextCompat.getColor(context, R.color.sky_blue_color_picker));
        colorPickerColors.add(ContextCompat.getColor(context, R.color.violet_color_picker));
        colorPickerColors.add(ContextCompat.getColor(context, R.color.white));
        colorPickerColors.add(ContextCompat.getColor(context, R.color.yellow_color_picker));
        colorPickerColors.add(ContextCompat.getColor(context, R.color.yellow_green_color_picker));
        return colorPickerColors;
    }
}
