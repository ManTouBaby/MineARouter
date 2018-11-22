package com.hrw.book.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hrw.book.R;
import com.hrw.book.entity.BooKBO;
import com.hrw.book.entity.HomeChoiceBO;
import com.hrw.common.utils.MtGlideUtils;
import com.hrw.common.widget.MtViewPage;
import com.hrw.smartrecyclerviewlibrary.SmartAdapter;
import com.hrw.smartrecyclerviewlibrary.SmartVH;

import java.util.ArrayList;
import java.util.List;

import static com.hrw.common.servicePath.BKInterface.ROOT_BOOK_IMG;

/**
 * @author:MtBaby
 * @date:2018/11/12 21:12
 * @desc:
 */
public class BKHomeListAdapter extends SmartAdapter<HomeChoiceBO> {
    Context mContext;

    public BKHomeListAdapter(Context context, @NonNull int layoutId) {
        super(layoutId);
        mContext = context;
    }

    @Override
    protected void bindView(SmartVH smartVH, HomeChoiceBO homeChoiceBO, int i) {
        TextView tvShowItemType;
        View verticalContainer;
        View verticalContainer2;
        View horizontalContainer;
        View horizontalContainer2;
        View horizontalContainer3;
        View horizontalContainer4;
        View horizontalContainer5;
        View horizontalContainer6;
        switch (homeChoiceBO.getItemType()) {
            case 1:
                smartVH.getText(R.id.tv_book_name).setText(homeChoiceBO.getBookList().getTitle());
                smartVH.getText(R.id.tv_book_decr).setText(homeChoiceBO.getBookList().getDescription());
                Glide.with(mContext).load(homeChoiceBO.getBookList().getImgUrl()).into(smartVH.getImage(R.id.iv_book_bg));
                break;
            case 4:
                smartVH.getText(R.id.tv_book_type1).setText(homeChoiceBO.getCategories().get(0).getCategoryName());
                smartVH.getText(R.id.tv_book_type2).setText(homeChoiceBO.getCategories().get(1).getCategoryName());
                smartVH.getText(R.id.tv_book_type3).setText(homeChoiceBO.getCategories().get(2).getCategoryName());
                smartVH.getText(R.id.tv_book_type4).setText(homeChoiceBO.getCategories().get(3).getCategoryName());
                break;
            case 5:
                tvShowItemType = smartVH.getText(R.id.tv_item_type_name);
                verticalContainer = smartVH.getViewById(R.id.i_vertical_1);
                verticalContainer2 = smartVH.getViewById(R.id.i_vertical_2);
                horizontalContainer = smartVH.getViewById(R.id.i_horizontal_1);
                horizontalContainer2 = smartVH.getViewById(R.id.i_horizontal_2);
                horizontalContainer3 = smartVH.getViewById(R.id.i_horizontal_3);

                tvShowItemType.setText(homeChoiceBO.getCategory());
                getViewShow1(verticalContainer, homeChoiceBO.getBooks().get(0));
                getViewShow1(verticalContainer2, homeChoiceBO.getBooks().get(1));
                getViewShow3(horizontalContainer, homeChoiceBO.getBooks().get(2));
                getViewShow3(horizontalContainer2, homeChoiceBO.getBooks().get(3));
                getViewShow3(horizontalContainer3, homeChoiceBO.getBooks().get(4));
                break;
            case 6:
                tvShowItemType = smartVH.getText(R.id.tv_item_type_name);
                horizontalContainer = smartVH.getViewById(R.id.i_horizontal_1);
                horizontalContainer2 = smartVH.getViewById(R.id.i_horizontal_2);
                horizontalContainer3 = smartVH.getViewById(R.id.i_horizontal_3);
                horizontalContainer4 = smartVH.getViewById(R.id.i_horizontal_4);
                horizontalContainer5 = smartVH.getViewById(R.id.i_horizontal_5);
                horizontalContainer6 = smartVH.getViewById(R.id.i_horizontal_6);

                tvShowItemType.setText(homeChoiceBO.getCategory());
                getViewShow3(horizontalContainer, homeChoiceBO.getBooks().get(0));
                getViewShow3(horizontalContainer2, homeChoiceBO.getBooks().get(1));
                getViewShow3(horizontalContainer3, homeChoiceBO.getBooks().get(2));
                getViewShow3(horizontalContainer4, homeChoiceBO.getBooks().get(3));
                getViewShow3(horizontalContainer5, homeChoiceBO.getBooks().get(4));
                getViewShow3(horizontalContainer6, homeChoiceBO.getBooks().get(5));
                break;
            case 7:
                tvShowItemType = smartVH.getText(R.id.tv_item_type_name);
                verticalContainer = smartVH.getViewById(R.id.i_vertical_1);
                horizontalContainer = smartVH.getViewById(R.id.i_horizontal_1);
                horizontalContainer2 = smartVH.getViewById(R.id.i_horizontal_2);
                horizontalContainer3 = smartVH.getViewById(R.id.i_horizontal_3);
                horizontalContainer4 = smartVH.getViewById(R.id.i_horizontal_4);
                horizontalContainer5 = smartVH.getViewById(R.id.i_horizontal_5);
                horizontalContainer6 = smartVH.getViewById(R.id.i_horizontal_6);

                tvShowItemType.setText(homeChoiceBO.getCategory());
                getViewShow1(verticalContainer, homeChoiceBO.getBooks().get(0));
                getViewShow3(horizontalContainer, homeChoiceBO.getBooks().get(1));
                getViewShow3(horizontalContainer2, homeChoiceBO.getBooks().get(2));
                getViewShow3(horizontalContainer3, homeChoiceBO.getBooks().get(3));
                getViewShow3(horizontalContainer4, homeChoiceBO.getBooks().get(4));
                getViewShow3(horizontalContainer5, homeChoiceBO.getBooks().get(5));
                getViewShow3(horizontalContainer6, homeChoiceBO.getBooks().get(6));
                break;
            case 12:
                tvShowItemType = smartVH.getText(R.id.tv_item_type_name);
                tvShowItemType.setText(homeChoiceBO.getCategory());
                MtViewPage viewPage = smartVH.getViewById(R.id.vp_book_banner);
                List<View> views = new ArrayList<>();
                List<BooKBO> booKBOS = homeChoiceBO.getBooks();
                for (int k = 0; k < booKBOS.size() / 3; k++) {
                    booKBOS.get(3 * k + 0);
                    booKBOS.get(3 * k + 1);
                    booKBOS.get(3 * k + 2);
                    View view = getViewShow4(booKBOS.get(3 * k + 0), booKBOS.get(3 * k + 1), booKBOS.get(3 * k + 2));
                    views.add(view);
                }
                viewPage.setDate(views);
                break;
        }
    }

    private View getViewShow4(final BooKBO booKBO1, final BooKBO booKBO2, final BooKBO booKBO3) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.banner_book_show, null);
        View view1 = view.findViewById(R.id.i_horizontal_1);
        View view2 = view.findViewById(R.id.i_horizontal_2);
        View view3 = view.findViewById(R.id.i_horizontal_3);
        getViewShow2(view1, booKBO1);
        getViewShow2(view2, booKBO2);
        getViewShow2(view3, booKBO3);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onBookClickListener != null) {
                    onBookClickListener.onBookClick(booKBO1);
                }
            }
        });
        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onBookClickListener != null) {
                    onBookClickListener.onBookClick(booKBO2);
                }
            }
        });
        view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onBookClickListener != null) {
                    onBookClickListener.onBookClick(booKBO3);
                }
            }
        });
        return view;
    }

    private void getViewShow2(View view, final BooKBO listItemBO) {
        ImageView imageView1 = view.findViewById(R.id.iv_book_bg);
        TextView textView1 = view.findViewById(R.id.tv_book_name);
        MtGlideUtils.bindIMG(mContext, ROOT_BOOK_IMG + listItemBO.getImg(), imageView1);
        textView1.setText(listItemBO.getName());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onBookClickListener != null) {
                    onBookClickListener.onBookClick(listItemBO);
                }
            }
        });
    }

    private View getViewShow1(View view, final BooKBO listItemBO) {
        ImageView ivBookBG = view.findViewById(R.id.iv_book_bg);
        TextView tvBookName = view.findViewById(R.id.tv_book_name);
        TextView tvBookDesc = view.findViewById(R.id.tv_book_desc);
        TextView tvBookAuthor = view.findViewById(R.id.tv_book_author);
        TextView tvBookType = view.findViewById(R.id.tv_book_type);
        MtGlideUtils.bindIMG(mContext, ROOT_BOOK_IMG + listItemBO.getImg(), ivBookBG);
        tvBookName.setText(listItemBO.getName());
        tvBookDesc.setText(listItemBO.getDesc());
        tvBookAuthor.setText(listItemBO.getAuthor());
        tvBookType.setText(listItemBO.getCName());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onBookClickListener != null) {
                    onBookClickListener.onBookClick(listItemBO);
                }
            }
        });
        return view;
    }

    private View getViewShow3(View view, final BooKBO listItemBO) {
        ImageView ivBookBG = view.findViewById(R.id.iv_book_bg);
        TextView tvBookName = view.findViewById(R.id.tv_book_name);
        TextView tvBookAuthor = view.findViewById(R.id.tv_book_author);
        MtGlideUtils.bindIMG(mContext, ROOT_BOOK_IMG + listItemBO.getImg(), ivBookBG);
        tvBookName.setText(listItemBO.getName());
        tvBookAuthor.setText(listItemBO.getAuthor());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onBookClickListener != null) {
                    onBookClickListener.onBookClick(listItemBO);
                }
            }
        });
        return view;
    }

    OnBookClickListener onBookClickListener;

    public void setOnBookClickListener(OnBookClickListener onBookClickListener) {
        this.onBookClickListener = onBookClickListener;
    }

    public interface OnBookClickListener {
        void onBookClick(BooKBO listItemBO);
    }
}
