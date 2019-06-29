package com.chaity.android.easy.move.bindingAdapter;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.chaity.android.easy.move.R;
import com.squareup.picasso.Picasso;

public class DeliveryBindingAdapter {

    @BindingAdapter("imgSrc")
    public  static  void setImgSrc(ImageView view, String imgUrl)
    {
        Picasso.get()
                .load(imgUrl)
                    .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_eye)
            .fit()
            .into(view);
    }
}
