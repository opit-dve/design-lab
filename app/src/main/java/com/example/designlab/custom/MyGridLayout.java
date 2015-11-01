package com.example.designlab.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.designlab.R;


/**
 * Custom layout that arranges children in a grid-like manner, optimizing for even horizontal and
 * vertical whitespace.
 */
public class MyGridLayout extends ViewGroup {

    public static enum ItemAlignment {
        LEFT,
        RIGHT,
        CENTER
    }

	private int mHSpacing = 0;
	private int mVSpacing = 0;

    private int mMaxChildWidth = 0;
    private int mMaxChildHeight = 0;

    private ItemAlignment mItemAlignment = ItemAlignment.CENTER;


    public MyGridLayout(Context context) {
        super(context, null);
    }


    public MyGridLayout(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        init(context, attrs);
    }


    public MyGridLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
    	TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyGridLayout);
		try {
			mHSpacing = a.getDimensionPixelSize(R.styleable.MyGridLayout_hSpacing, 0);
			mVSpacing = a.getDimensionPixelSize(R.styleable.MyGridLayout_vSpacing, 0);
		} finally {
			a.recycle();
		}
    }

    public void setItemAlignment(ItemAlignment itemAlignment) {
        mItemAlignment = itemAlignment;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    	int widthSize = MeasureSpec.getSize(widthMeasureSpec) - getPaddingRight();
    	int hSpacingReal = mHSpacing;
    	int childsPerLine = 1;
    	
    	int width = 0;
		int height = getPaddingTop();
    	
        mMaxChildWidth = 0;
        mMaxChildHeight = 0;


        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }
            
            //child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);


            mMaxChildWidth = Math.max(mMaxChildWidth, child.getMeasuredWidth());
            mMaxChildHeight = Math.max(mMaxChildHeight, child.getMeasuredHeight());
        }

        
        // Determine childs per line
        int tmpWidth = 0;
        for (int i = 1; i <= 1000; i++) {
        	
        	tmpWidth = i * mMaxChildWidth + (i+1) * mHSpacing;
        	if (tmpWidth > widthSize) {
        		break;
        	}
        	childsPerLine = i;
        }
        
        // Determine real spacing
        hSpacingReal = Math.round((widthSize - (childsPerLine * mMaxChildWidth)) / (childsPerLine + 1f));

        // Measure again for each child to be exactly the same size.
        int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(
                mMaxChildWidth, MeasureSpec.EXACTLY);
        int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
                mMaxChildHeight, MeasureSpec.EXACTLY);


        int childsOnTheLine = 1;
        int currentWidth = getPaddingLeft();
        
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }

            
            LayoutParams lp = (LayoutParams) child.getLayoutParams();

            if (childsOnTheLine > childsPerLine) {
            	height += mMaxChildHeight + mVSpacing;
            	childsOnTheLine = 1;
            	currentWidth = getPaddingLeft();
            }

            // left / right / center alligment
            if (childsOnTheLine == 1) {
                switch (mItemAlignment) {
                    case LEFT:
                        break;
                    case RIGHT:
                        currentWidth += (hSpacingReal * 2);
                        break;
                    default:
                        currentWidth += hSpacingReal;
                        break;
                }
            }
            else {
                currentWidth += hSpacingReal;
            }

            lp.x = currentWidth;
            lp.y = height;
            
            currentWidth += mMaxChildWidth;
            childsOnTheLine++;

            child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
        }

        width = widthSize + getPaddingRight();
		height += mMaxChildHeight + getPaddingBottom();

        setMeasuredDimension(
                resolveSize(width, widthMeasureSpec),
                resolveSize(height, heightMeasureSpec));
    }
    
    @Override
	protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
		return p instanceof LayoutParams;
	}

	@Override
	protected LayoutParams generateDefaultLayoutParams() {
		return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	}

	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		return new LayoutParams(getContext(), attrs);
	}
	
	@Override
	protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
		return new LayoutParams(p.width, p.height);
	}

	public static class LayoutParams extends ViewGroup.LayoutParams {
		int x;
		int y;

		public LayoutParams(Context context, AttributeSet attrs) {
			super(context, attrs);
		}

		public LayoutParams(int w, int h) {
			super(w, h);
		}
	}

    @Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		final int count = getChildCount();
		for (int i = 0; i < count; i++) {
			View child = getChildAt(i);
			LayoutParams lp = (LayoutParams) child.getLayoutParams();
			child.layout(lp.x, lp.y, lp.x + child.getMeasuredWidth(), lp.y + child.getMeasuredHeight());
		}
	}
}
