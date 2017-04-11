package karikuncheva.dominosapp.model;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * Created by Mariela Zviskova on 10.4.2017 г..
 */

public class CustomLinearLayoutManager extends LinearLayoutManager {
    public CustomLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);

    }

    // it will always pass false to RecyclerView when calling "canScrollVertically()" method.
    @Override
    public boolean canScrollVertically() {
        return false;
    }
}