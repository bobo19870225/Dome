package com.yy.dome.tool;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

public  class ItemAnimatorAbstrc  extends SimpleItemAnimator {
 // item 移除回调
        @Override public boolean animateRemove(RecyclerView.ViewHolder holder) {
return false; }

// item 添加回调
         @Override public boolean animateAdd(RecyclerView.ViewHolder holder) { return false; }


// 添加，移动更新时，其它 item 的动画执行
        @Override
public boolean animateMove(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
return false; }

// item 更新回调
         @Override
public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromLeft, int fromTop, int toLeft, int toTop) {
return false;
 }

 // 控制执行动画的地方
    @Override public void runPendingAnimations() {
        }
        // 停止某个 item 的动画
    @Override public void endAnimation(RecyclerView.ViewHolder item) {
        }

// 停止所有 item 动画
    @Override public void endAnimations() {
        }
        @Override public boolean isRunning() { return false; }
}
