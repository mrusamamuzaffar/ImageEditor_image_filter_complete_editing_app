package com.deep.photoeditor;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class StickerBSFragment extends BottomSheetDialogFragment {

    public StickerBSFragment() {
    }

    private StickerListener mStickerListener;

    public void setStickerListener(StickerListener stickerListener) {
        mStickerListener = stickerListener;
    }

    public interface StickerListener {
        void onStickerClick(Bitmap bitmap);
    }

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };


    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.fragment_bottom_sticker_emoji_dialog, null);
        dialog.setContentView(contentView);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
        ((View) contentView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));
        RecyclerView rvEmoji = contentView.findViewById(R.id.rvEmoji);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        rvEmoji.setLayoutManager(gridLayoutManager);
        StickerAdapter stickerAdapter = new StickerAdapter();
        rvEmoji.setAdapter(stickerAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public class StickerAdapter extends RecyclerView.Adapter<StickerAdapter.ViewHolder> {

        int[] stickerList = new int[]{R.drawable.a,R.drawable.aaaa,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g,
                R.drawable.h,R.drawable.i,R.drawable.j,R.drawable.k,R.drawable.l,R.drawable.m,R.drawable.n,R.drawable.o,R.drawable.p,R.drawable.q,
                R.drawable.r,R.drawable.s,R.drawable.t,R.drawable.u,R.drawable.v,R.drawable.w,R.drawable.x,R.drawable.y,R.drawable.z,R.drawable.ab,
                R.drawable.ac,R.drawable.ad,R.drawable.ae,R.drawable.af,R.drawable.ag,R.drawable.ah,R.drawable.ai,R.drawable.aj,R.drawable.ak,
                R.drawable.al,R.drawable.am,R.drawable.an,R.drawable.ao,R.drawable.ap,R.drawable.aq,R.drawable.ar,R.drawable.as,R.drawable.at,
                R.drawable.au,R.drawable.av,R.drawable.aw,R.drawable.ax,R.drawable.ay,R.drawable.az,R.drawable.ba,R.drawable.bb,R.drawable.bc,
                R.drawable.bd,R.drawable.be,R.drawable.bf,R.drawable.bg,R.drawable.bh,R.drawable.bi,R.drawable.bj,R.drawable.bk,R.drawable.bl,
                R.drawable.bm,R.drawable.bn,R.drawable.bo,R.drawable.bp,R.drawable.bq,R.drawable.br,R.drawable.bs,R.drawable.bt,R.drawable.bu,
                R.drawable.bv,R.drawable.bw,R.drawable.bx,R.drawable.by,R.drawable.bz,R.drawable.ca,R.drawable.cb,R.drawable.cc,R.drawable.cd,
                R.drawable.ce,R.drawable.cf,R.drawable.cg,R.drawable.ch,R.drawable.ci,R.drawable.cj,R.drawable.ck,R.drawable.cl,R.drawable.cm,
                R.drawable.cn,R.drawable.co,R.drawable.cp,R.drawable.cq,R.drawable.cr,R.drawable.cs,R.drawable.ct,R.drawable.cu,R.drawable.cv,
                R.drawable.cw,R.drawable.cx,R.drawable.cy,R.drawable.cz,R.drawable.da,R.drawable.db,R.drawable.dc,R.drawable.dd,R.drawable.de,
                R.drawable.df,R.drawable.dg,R.drawable.dh,R.drawable.di,R.drawable.dj,R.drawable.dk,R.drawable.dl,};



        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sticker, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.imgSticker.setImageResource(stickerList[position]);
        }

        @Override
        public int getItemCount() {
            return stickerList.length;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imgSticker;

            ViewHolder(View itemView) {
                super(itemView);
                imgSticker = itemView.findViewById(R.id.imgSticker);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mStickerListener != null) {
                            mStickerListener.onStickerClick(
                                    BitmapFactory.decodeResource(getResources(),
                                            stickerList[getLayoutPosition()]));
                        }
                        dismiss();
                    }
                });
            }
        }
    }
}