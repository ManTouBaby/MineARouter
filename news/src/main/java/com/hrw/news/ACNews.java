package com.hrw.news;

import android.widget.TextView;

import com.hrw.common.baseMVVM.BaseActivity;
import com.hrw.common.utils.MtGraphicMixedUtils;

public class ACNews extends BaseActivity {
    TextView tvDynamicContent;

    @Override
    protected void initView() {
        tvDynamicContent = findViewById(R.id.tv_new_content);

        String html = "幸福不是你的爱人多漂亮，多帅气，也不是有多少家产，有多少钱，而是心心相印，患难与共，幸福快乐；幸福不是成功时赢得多少喝彩，而是在失意或遇到困难时有个声音对你说：朋友别倒下，我来帮你支撑这一切；幸福不是听多少甜言蜜语，而是在最困难的时候有人对你说：别怕，再大的困难我也和你一起扛！" +
                "<img src='http://www.qqpk.cn/Article/UploadFiles/201411/20141116135722282.jpg'/>" +
                "我们渴望成功，首先要志在成功，要盯着目标向前走，视线不要左右飘忽，心态不易随地转移。要坚信成功不是梦幻，而就在我们脚下延伸，需要我们矢志不移地前行。要深知语言的祝愿很无力，行动才是最好的祈祷。成功的帷幕随时都有可能拉开，就看我们是愿意纵情地舞蹈，还是选择仓皇的逃避。" +
                "<img src='http://h.hiphotos.baidu.com/image/pic/item/d000baa1cd11728b2027e428cafcc3cec3fd2cb5.jpg'/>" +
                "心里面真诚的把自己当一个路人甲，就不怕别人把自己当路人假。真诚的把自己的错误数落一遍，就不怕别人的数落。真诚的把自己当成大人物的去做事，往往真的可以成大事。低调做人，高调做事。" +
                "<img src='http://img.61gequ.com/allimg/2011-4/201142614314278502.jpg' />";

        tvDynamicContent.setText(MtGraphicMixedUtils.setSource(this, tvDynamicContent, html));
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int createLayout() {
        return R.layout.activity_acnews;
    }
}
