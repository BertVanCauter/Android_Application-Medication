package bertvancauter.softdev.kuleuven.medication;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ExampleViewHolder>
{
    private ArrayList<ExampleMedicatieObject> mExampleMedicatieObjectArrayList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener
    {
        void onItemClick(int position);
        //void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }



    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        //public ImageView mDeleteImage;


        public ExampleViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.TextView);
            mTextView2 = itemView.findViewById(R.id.TextView2);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION);
                        {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public Adapter(ArrayList<ExampleMedicatieObject> exampleMedicatieObjectArrayList){
        mExampleMedicatieObjectArrayList = exampleMedicatieObjectArrayList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.medicatie_item,viewGroup,false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder exampleViewHolder, int i) {
        ExampleMedicatieObject currentMedicatieObject = mExampleMedicatieObjectArrayList.get(i);

        exampleViewHolder.mImageView.setImageResource(currentMedicatieObject.getmImageResource());
        exampleViewHolder.mTextView1.setText(currentMedicatieObject.getText1());
        exampleViewHolder.mTextView2.setText(currentMedicatieObject.getText2());
    }

    @Override
    public int getItemCount() {
        return mExampleMedicatieObjectArrayList.size();
    }
}
