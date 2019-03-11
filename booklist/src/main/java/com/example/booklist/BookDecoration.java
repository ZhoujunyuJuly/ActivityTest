package com.example.booklist;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class BookDecoration extends RecyclerView.ItemDecoration {
    private float mDividerHight;
    private Paint paint;

    public BookDecoration() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
    }


    @Override
    public void getItemOffsets(Rect rect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(rect, view, parent, state);
        if (parent.getChildAdapterPosition(view) != 0) {
            rect.top = 1;
            mDividerHight = 1;
        }
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int index = parent.getChildAdapterPosition(view);

            if (index == 0) {
                continue;
            }
            float dividerTop = view.getTop() - mDividerHight;
            float dividerBottom = view.getTop();
            float dividerLeft = parent.getPaddingLeft();
            float dividerRight = parent.getWidth() - parent.getPaddingRight();

            c.drawRect(dividerLeft, dividerTop, dividerRight, dividerBottom, paint);
        }

    }


}
