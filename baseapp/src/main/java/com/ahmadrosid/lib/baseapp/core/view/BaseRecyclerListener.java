/*
 * Copyright (c) 2017 Ahmad Rosid.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.ahmadrosid.lib.baseapp.core.view;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * Created by ocittwo on 1/28/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */

public abstract class BaseRecyclerListener extends RecyclerView.OnScrollListener {

    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 3;
    private int firstVisibleItem;
    private int visibleItemCount;
    private int totalItemCount;
    private int currentPage = 0;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;


    public BaseRecyclerListener(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

    public BaseRecyclerListener(GridLayoutManager gridLayoutManager) {
        this.gridLayoutManager = gridLayoutManager;
    }

    public BaseRecyclerListener(StaggeredGridLayoutManager staggeredGridLayoutManager) {
        this.staggeredGridLayoutManager = staggeredGridLayoutManager;
    }

    public BaseRecyclerListener(LinearLayoutManager linearLayoutManager, int visibleThreshold) {
        this.linearLayoutManager = linearLayoutManager;
        this.visibleThreshold = visibleThreshold;
    }

    public BaseRecyclerListener(GridLayoutManager gridLayoutManager, int visibleThreshold) {
        this.gridLayoutManager = gridLayoutManager;
        this.visibleThreshold = visibleThreshold;
    }

    public BaseRecyclerListener(StaggeredGridLayoutManager staggeredGridLayoutManager, int visibleThreshold) {
        this.staggeredGridLayoutManager = staggeredGridLayoutManager;
        this.visibleThreshold = visibleThreshold;
    }


    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        if (linearLayoutManager != null) {
            totalItemCount = linearLayoutManager.getItemCount();
            firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
        } else if (gridLayoutManager != null) {
            totalItemCount = gridLayoutManager.getItemCount();
            firstVisibleItem = gridLayoutManager.findFirstVisibleItemPosition();
        } else if (staggeredGridLayoutManager != null) {
            totalItemCount = staggeredGridLayoutManager.getItemCount();
            int[] tmp = null;
            tmp = staggeredGridLayoutManager.findFirstCompletelyVisibleItemPositions(tmp);
            if (tmp != null && tmp.length > 0) {
                firstVisibleItem = tmp[0];
            }
        }

        if (loading && totalItemCount > previousTotal) {
            loading = false;
            previousTotal = totalItemCount;
        }

        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            currentPage++;
            onLoadMore(currentPage);
            loading = true;
        }
    }

    public abstract void onLoadMore(int currentPage);
}
