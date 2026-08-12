package com.yolatino.yolatino;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.yolatino.yolatino.ui.FrmHorasCompra;
import com.yolatino.yolatino.ui.FrmHorasConsumo;

public class AdaptadorTabLayout extends FragmentStateAdapter {

    private final FrmHorasCompra frmHorasCompra;
    private final FrmHorasConsumo frmHorasConsumo;

    public AdaptadorTabLayout(@NonNull Fragment fragment) {

        super(fragment);

        this.frmHorasCompra = new FrmHorasCompra();
        this.frmHorasConsumo = new FrmHorasConsumo();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                return frmHorasConsumo;
            case 1:
                return frmHorasCompra;
            default:
                return frmHorasConsumo;
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
