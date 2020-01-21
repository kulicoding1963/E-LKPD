package com.elkpd.apps.ui;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.elkpd.apps.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;

import java.util.Objects;


public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ImageView facebook = root.findViewById(R.id.facebook);
        ImageView email = root.findViewById(R.id.email);
        ImageView instagram = root.findViewById(R.id.instagram);
        ImageView whatsapp = root.findViewById(R.id.whatsapp);
        ImageView twitter = root.findViewById(R.id.twitter);

        PDFView pdf = root.findViewById(R.id.pdf);
        pdf.fromAsset("welcome.pdf")
                .spacing(0)
                .pageFitPolicy(FitPolicy.WIDTH)
                .load();

        facebook.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                try {
                    Objects.requireNonNull(getContext()).getPackageManager()
                            .getPackageInfo("com.facebook.katana", 0); //Checks if FB is even installed.
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/fitra.p.agung"))); //Trys to make intent with FB's URI
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/fitra.p.agung"))); //catches and opens a url to the desired page
                }
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pengembang = "fitrapurnamaagung@gmail.com";
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + pengembang));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Kritik dan Saran");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Halo. Saya mau bertanya..");

                startActivity(Intent.createChooser(emailIntent, "Kritik dan Saran"));
            }
        });

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://instagram.com/_u/fitra_purnamaagung_");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");
                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/fitra_purnamaagung_")));
                }
            }
        });

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String packageName = "com.whatsapp";
                if (isAppInstalled(requireActivity(), packageName)) {
                    try {
                        String text = "Halo,saya mau bertanya mengenai aplikasi ";// Replace with your message.

                        String toNumber = "+6287798773144"; // Replace with mobile phone number without +Sign or leading zeros.


                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + toNumber + "&text=" + text));
                        startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else
                    Toast.makeText(getContext(), "App not installed", Toast.LENGTH_SHORT).show();
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String twitter_user_name = "fitrapurnama_";
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=" + twitter_user_name)));
                }catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/#!/" + twitter_user_name)));
                }
            }
        });
        return root;
    }

    private boolean isAppInstalled(Activity activity, String packageName) {
        PackageManager pm = activity.getPackageManager();
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return false;
    }
}