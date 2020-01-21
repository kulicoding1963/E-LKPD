package com.elkpd.apps.adapter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.elkpd.apps.R;
import com.elkpd.apps.model.HasilEvaluasi;
import com.elkpd.apps.ui.AktivitasFragment;
import com.elkpd.apps.ui.Evaluasi.Evaluasi11Fragment;
import com.elkpd.apps.ui.Evaluasi.Evaluasi12Fragment;
import com.elkpd.apps.ui.Evaluasi.Evaluasi13Fragment;
import com.elkpd.apps.ui.Evaluasi.Evaluasi21Fragment;
import com.elkpd.apps.ui.Evaluasi.Evaluasi22Fragment;
import com.elkpd.apps.ui.Evaluasi.Evaluasi23Fragment;
import com.elkpd.apps.ui.Evaluasi.Evaluasi24Fragment;
import com.elkpd.apps.ui.Evaluasi.Evaluasi31Fragment;
import com.elkpd.apps.ui.Evaluasi.Evaluasi32Fragment;


public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private String url;
    private int type;
    private String hash;
    private String name;
    private HasilEvaluasi hasilEvaluasi;

    public SectionsPagerAdapter(Context context, @NonNull FragmentManager fm, String url,int type,String hash,String name,HasilEvaluasi hasilEvaluasi) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
        this.url = url;
        this.type = type;
        this.hash = hash;
        this.name = name;
        this.hasilEvaluasi= hasilEvaluasi;
    }

    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.soal,
            R.string.jawaban,
    };

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        switch (position) {
            case 0:
                bundle.putString("URL", url);
                fragment = new AktivitasFragment();
                fragment.setArguments(bundle);
                break;
            case 1:
                bundle.putString("HASH", hash);
                bundle.putString("NAME", name);
                bundle.putParcelable("DATA", hasilEvaluasi);
                if(type == 11){
                    fragment = new Evaluasi11Fragment();
                }else if(type == 12){
                    fragment = new Evaluasi12Fragment();
                }else if(type == 13){
                    fragment = new Evaluasi13Fragment();
                }else if(type == 21){
                    fragment = new Evaluasi21Fragment();
                }else if(type == 22){
                    fragment = new Evaluasi22Fragment();
                }else if(type == 23){
                    fragment = new Evaluasi23Fragment();
                }else if(type == 24){
                    fragment = new Evaluasi24Fragment();
                }else if(type == 31){
                    fragment = new Evaluasi31Fragment();
                }else if(type == 32){
                    fragment = new Evaluasi32Fragment();
                }
                assert fragment != null;
                fragment.setArguments(bundle);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + position);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(TAB_TITLES[position]);
    }
}
