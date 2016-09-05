/*
 * Copyright 2016. Carlos Ottoboni
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.ottoboni.hqtracker.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.android.multiselector.MultiSelector;
import com.bignerdranch.android.multiselector.SwappingHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.ottoboni.hqtracker.R;
import br.com.ottoboni.hqtracker.model.ComicBook;
import br.com.ottoboni.hqtracker.model.enums.ComicBookStatus;
import br.com.ottoboni.hqtracker.util.FontUtil;

public class ComicBookAdapter extends RecyclerView.Adapter <ComicBookAdapter.ViewHolder> {

    private List<ComicBook> mItems;
    private Context mContext;

    private MultiSelector mMultiSelector;

    private ViewHolder.ComicBookListener mItemClickListener;

    public ComicBookAdapter(List<ComicBook> items, MultiSelector selector, ViewHolder.ComicBookListener
        listener) {
        mItems = items;
        mMultiSelector = selector;
        mItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_comic_book,
            parent, false);

        ViewHolder viewHolder = new ViewHolder(rootView, mMultiSelector, mItemClickListener);

        setTypeface(viewHolder);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mComicBookTitle.setText(mItems.get(position).getTitle());

        String url = mItems.get(position).getImgLarge();

        if (url != null && !url.isEmpty()) {
            Picasso.with(mContext)
                .load(url)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(holder.mComicBookCover);
        }
        else {
            holder.mComicBookCover.setImageDrawable(mContext.getResources().getDrawable(R.drawable
                .placeholder));
        }

        int volume = mItems.get(position).getVolume();
        if (volume != 0) {
            holder.mVolume.setVisibility(View.VISIBLE);
            holder.mLblVolume.setVisibility(View.VISIBLE);
            holder.mVolume.setText(Integer.toString(volume));
        }
        else {
            holder.mVolume.setVisibility(View.INVISIBLE);
            holder.mLblVolume.setVisibility(View.INVISIBLE);
        }


        String spine = mItems.get(position).getBookSpine();

        if (spine != null) {
            holder.mSpine.setText(spine);
            holder.mSpine.setVisibility(View.VISIBLE);
            holder.mLblSpine.setVisibility(View.VISIBLE);
        }
        else {
            holder.mLblSpine.setVisibility(View.INVISIBLE);
            holder.mSpine.setVisibility(View.INVISIBLE);
        }

        int readOrder = mItems.get(position).getReadOrder();

        if (readOrder != 0) {
            holder.mReadOrder.setVisibility(View.VISIBLE);
            holder.mLblReadOrder.setVisibility(View.VISIBLE);
            holder.mReadOrder.setText(Integer.toString(readOrder));
        }
        else {
            holder.mReadOrder.setVisibility(View.INVISIBLE);
            holder.mLblReadOrder.setVisibility(View.INVISIBLE);
        }

        ComicBookStatus status = mItems.get(position).getStatus();

        Drawable statusColor = holder.mComicBookStatus.getBackground();

        if (statusColor instanceof GradientDrawable) {
            GradientDrawable gradientDrawable = (GradientDrawable) statusColor;

            gradientDrawable.setColor(ComicBookStatus.getStatusColor(status));
        }

        holder.mLblStatus.setText(ComicBookStatus.getStatusText(status));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setTypeface(ViewHolder viewHolder) {

        viewHolder.mComicBookTitle.setTypeface(FontUtil.ROBOTO_MEDIUM);
        viewHolder.mVolume.setTypeface(FontUtil.ROBOTO_REGULAR);
        viewHolder.mSpine.setTypeface(FontUtil.ROBOTO_REGULAR);
        viewHolder.mReadOrder.setTypeface(FontUtil.ROBOTO_REGULAR);
        viewHolder.mLblStatus.setTypeface(FontUtil.ROBOTO_REGULAR);
        viewHolder.mLblVolume.setTypeface(FontUtil.ROBOTO_REGULAR);
        viewHolder.mLblSpine.setTypeface(FontUtil.ROBOTO_REGULAR);
        viewHolder.mLblReadOrder.setTypeface(FontUtil.ROBOTO_REGULAR);

    }

    public static class ViewHolder extends SwappingHolder implements View.OnClickListener, View.OnLongClickListener {

        private ImageView mComicBookCover;
        private ImageView mComicBookStatus;
        private TextView mComicBookTitle;
        private TextView mVolume;
        private TextView mSpine;
        private TextView mReadOrder;
        private TextView mLblStatus;
        private TextView mLblVolume;
        private TextView mLblSpine;
        private TextView mLblReadOrder;

        private ComicBookListener mListener;
        private MultiSelector mMultiSelector;

        public ViewHolder(View itemView, MultiSelector multiSelector, ComicBookListener listener) {
            super(itemView, multiSelector);

            mComicBookCover = (ImageView) itemView.findViewById(R.id.comic_book_cover);
            mComicBookStatus = (ImageView) itemView.findViewById(R.id.comic_book_status);
            mComicBookTitle = (TextView) itemView.findViewById(R.id.txt_title);
            mVolume = (TextView) itemView.findViewById(R.id.txt_volume);
            mSpine = (TextView) itemView.findViewById(R.id.txt_spine);
            mReadOrder = (TextView) itemView.findViewById(R.id.txt_read_order);
            mLblStatus = (TextView) itemView.findViewById(R.id.lbl_status);
            mLblVolume = (TextView) itemView.findViewById(R.id.lbl_volume);
            mLblSpine = (TextView) itemView.findViewById(R.id.lbl_spine);
            mLblReadOrder = (TextView) itemView.findViewById(R.id.lbl_read_order);

            mListener = listener;
            mMultiSelector = multiSelector;

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            setSelectionModeStateListAnimator(null);

        }

        @Override
        public void onClick(View view) {
            mListener.onItemClick(this, mMultiSelector);

        }

        @Override
        public boolean onLongClick(View view) {
            mListener.onItemLongClick(this, mMultiSelector);
            return true;
        }

        public interface ComicBookListener {
            void onItemClick(ViewHolder view, MultiSelector multiSelector);
            void onItemLongClick(ViewHolder view, MultiSelector multiSelector);
        }
    }
}
