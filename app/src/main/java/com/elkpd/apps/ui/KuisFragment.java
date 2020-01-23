package com.elkpd.apps.ui;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.elkpd.apps.HomeActivity;
import com.elkpd.apps.R;
import com.elkpd.apps.model.Kuis;
import com.elkpd.apps.tools.Utils;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class KuisFragment extends Fragment {

    String[][] question = {
            {"1", "Tidak seimbangnya hubungan eksositem di daerah tersebut", "Kualitas lingkungan di daerah tersebut mulai menurun", "Kerusakan lingkungan berupa hutan akibat ulah manusia", "Siklus hidup belelang setan yang begitu sangat cepat", "Kekurangan makanan sehingga menyerang ladang warga", "Bacalah cuplikan artikel berikut!", "Gunungkidul, news.detik.com – Hama belalang setan atau Aularches miliaris merusak tanaman milik warga di wilayah Baleharjo dan Karangrejek, Wonosari, Gunungkidul. Belalang tersebut memakan tanaman seperti pohon jati, kelapa dan jagung di tegalan/lading. Serangan hama belalang setan ini cukup meresahkan petani selama berberapa waktu terakhir ini. Dinas Pertanian dan Pangan Gunungkidul berupaya mengendalikan populasi belelang setan. Kepala Dinas Pertanian dan Pangan Gunungkidul, Bambang Wisnu Broto menjelaskan pihaknya tidak akan memakai bahan kimia untuk membrantas belalang setan. Namun pihaknya akan menangkapnya secara manual.", "Berdasarkan penjelasan di atas, bahwa peledakan belalang setan menunjukan salah satu indikator…"},
            {"0", "Tidak terjadi peledakan populasi pada salah satu komponen biotiknya", "Aliran energi dan materi berjalan searah dari konsumen ke produsen", "Aliran energi produsen ke konsumen paling cepat terjadi", "Pertambahan populasi masing-masing komponen sama", "Dihuni oleh tumbuhan, hewan, dan mikroorganisme saprofit", "Suatu lingkungan yang seimbang ditandai dengan…", "", ""},
            {"0", "Rencana tata ruang wilayah nasional", "Koordinasi penataan ruang daerah", "Rencana peraturan daerah setempat", "Pengelolaan lingkungan hidup", "Perlindungan lingkungan hidup", "Kawasan karst di Kabupaten Gunungkidul merupakan Kawasan lindung yang tidak boleh ditimbang berdasarkan Peraturan Pemerintah No. 26 Tahun 2008 tentang…", "", ""},
            {"0", "Peraturan pemerintah terkait perlindungan dan pengelolaan lingkungan hidup", "Peraturan pemerintah tentang rencana tata ruang wilayah nasional", "Peraturan pemerintah terkait perlindungan kerusakan hutan setempat", "Peraturan pemerintah tentang rencana pengelolaan daerah setempat", "Peraturan pemerintah tentang penataan ruang daerah setempat", "Larangan penambangan Kawasan karst di Kabupaten Gunungkidul juga mengacu pada peraturan UU No. 32 Tahun 2009 tentang…", "", ""},
            {"1", "Menyebabkan gagal penen bagi para petani", "Menyebabkan kerusakan lahan pertanian", "Terjadi pencemaran tanah berupa kekeringan", "Kekeringan mengakibatkan munculnya hama", "Menimbulkan radiasi panas di lahan petani", "Bacalah cuplikan artikel berikut!", "Yogyakarta, Kompas.com – Dampak kekeringan dirasakan oleh wilayah Gunungkidul, Yogyakarta. Kepala Dinas Pertanian dan Pangan Kabupaten Gunungkidul Bambang Wisnu Broto saat dihubungi, Selasa (2/7/2019). Mengatakan bahwa “Data lahan puso dilihat dari perbandingan tahun 2017 lalu yaitu tidak ada yang mengalami puso lahan pertanian di Kabupaten Gunungkidul, pada tahun 2018 ada 32 hektar yang mengalami puso, sedangkan pada tahun ini ada 1.918 hektar,” kata dia.", "Penjelesan di atas merupakan permasalahan lingkungan yang muncul karena dampak biofisik secara alami. Apakah yang akan ditumbulkan dari dampak tersebut…"},
            {"1", "Kerusakan ekosistem karst dan resiko kekeringan", "Mengakibatkan tanah longsor dan mudah banjir", "Pengurangan daya serap air tanah dan kualitas tanah", "Pemanasan global akibat efek bangunan hotel", "Mengakibatkan pencemaran tanah, udara, dan air", "Bacalah cuplikan artikel berikut!", "Mongabay.co.id – Perbukitan kart di sekitar Pantai Seruni, Desa Tepus, Kecamatan Tepus, gunungkidul, Yogyakarta, terkikis pembangunan resort (hotel) South Mountain Paradise oleh PT. Gunung Samudra Tirtomas (GST). Menurut Himawan Kurnia Koordinator Koalisi Masyarakat Peduli Pegunungan Sewu (KMPPS) “pembangunan hotel sudah berlangsung namun IMB dan Amdal belum ada. Ini pelanggaran hukum”. Pemda wajib melindungi KBAK Gunung Sewu sebagai Kawasan lindung geologi dari perusakan dan pemanfaatan yang mengubah bentang alamnya.", "Berdasarkan permasalahan di atas dampak lingkungan yang akan muncul dari pembangunan hotel tersebut adalah…"},
            {"1", "ISPA", "Batuk", "Pilek", "Asma", "Demam", "Bacalah cuplikan artikel berikut!", "Wonosari, gunungkidul.sorot.co – Semakin majunya kabupaten Gunungkidul baik dari segi ekonomi maupun pariwisata berdampaak pada banyaknya kendaraan yang dimiliki warga Gunungkidul. Sehingga, kepadatan kendaraan di jalan raya juga mengamali peningkatan baik kendaraan warga maupun wisatawan yang berkunjung. Akibat dari banyaknya kendaraan tersebut, menyebabkan polusi atau pencemaran udara. Selain kendaraan, banyak pabrik-pabrik yang berdiri dan juga sangat andil dalam meingkatkan polusi udara yang terjadi.", "Berdasarkan artikel di atas pencemaran udara dari asap kendaraan atau pabrik dapat menyebabkan penyakit…"},
            {"0", "Kerena fungsi penyerapan karbondioksida di atmosfer terganggu", "Karena daerah kasrt gunung sewu tersebut menjadi gundul dan gersang", "Karena ekosistem di daerah gunung sewu sudah tidak seimbang lagi", "Flora dan fauna di daerah karst gunung sewu tidak bisa bertahan hidup", "Karena fungsi penyerapan air tanah di kawasan tersebut terganggu", "Aktivitas penambangan karst pegunungan sewu, padatnya kendaraan penduduk dan pengunjung, serta berdirinya pabrik-pabrik di Daerah Gunungkidul tentunya berpotensi menimbulkan perubahan lingkungan dan perubahan iklim. Mengapa penambangan karst pegunungan sewu berpotensi menimbulkan perubahan iklim…", "", ""},
            {"0", "Baladewi, Rumongso Handarbeni, wajib Hangrungkepi, Mulat Sarira Hangrosowani, artinya merasa memiliki, saling menjaga dan melindungi, harus berani mempertahankan bila ada yang mengusik.", "Pikukuh karuhun yaitu kepercayaan yang melarang masyarakatnya memasuki hutan, menebang pohon, membuka ladang dan mengambil hasil hutan lainnya.", "Pranoto mongso yakni aturan bercocok tanam yang didasarkan pada tanda-tanda alam  dalam mongso yang berkaitan untuk menghindari eksploitasi sumber daya alam sehingga dapat menjaga keseimbangan ekosistem.", "Karampuang yaitu komunitas adat yang memiliki norma-norma adat untuk menjaga hutan seperti larangan menyadap enau (aren) di pagi hari agar tidak mengganggu hewan yang sedang mencari makan di pagi hari.", "Piplantri yang mengharuskan menanam 111 pohon ketika seorang bayi perempuan lahir dan Nyabuk Gunung yaitu cara bercocok tanam dengan membuat teras sawah yang dibentuk menurut garis kontur untuk menghindari terjadinya longsor.", "Kearifan lokal atau local wisdom diartikan sebagai nilai-nilai luhur yang berlaku dalam tata kehidupan masyarakat. Peran dari kearifan lokal salah satunya, yaitu melindungi dan mengelola lingkungan hidup secara lestari. Dibawah ini merupakan kearifan lokal Daerah Gunungkidul yang berpengaruh dalam upaya pemeliharaan ekosistem, yaitu:", "", ""},
            {"1", "Melakukan rehabilitasi atau reklamasi disetiap bekas tahapan proses pertambangan untuk dikembangkan kembali menjadi hutan dan dijaga", "Melakukan rehabilitasi atau reklamasi disetiap bekas tahapan proses pertambangan untuk dikembangkan sebagai lahan pertanian bagi masyarakat", "Mengolah bekas tambang sebagai tempat wisata yang menarik dan unik serta pengolahan tersebut dapat meningkatkan perekonomian masyarakat", "Mengolah bekas tambang sebagai tempat peternakan unggas yang dapat dijadikan sebagai usaha bagi masyarakat untuk mendukung perekonomiannya", "Melakukan rehabilitasi atau reklamasi disetiap bekas tahapan proses pertambangan untuk dikembangkan menjadi bendungan untuk menampung air", "Bacalah cuplikan artikel berikut!", "Mongabay.co.id - Penambangan di kawasan karst (batu gamping) di Kabupaten Gunungkidul Yogyakarta yang terus terjadi sampai saat ini memberikan dampak besar terhadap kelestarian kawasan tersebut. Terutama terhadap kondisi air bawah tanah dan ekosistem disekitarnya. Padahal kawasan karst memiliki potensi dan manfaat yang penting bagi ekosistem dan manusia. Potensi itu antara lain sebagai daerah tangkapan dan penampung air bawah tanah, habitat berbagai satwa khas dan unik, serta sebagai lokasi wisata alam, budaya, dan ilmiah.", "Berdasarkan permasalahan di atas, solusi atau upaya apakah yang dapat dilakukan saat ini untuk mengatasi permasalahan lingkungan tersebut…"},
            {"1", "Menggalakkan kembali Pendidikan lingkungan di sekolah maupun di masyarakat", "Melaksanakan kampanye 6R yakni (redesign, reduce, remove, reuse, recycle, dan recover)", "Memperhentikan pertambangan-pertambangan yang mengakibatkan lahan kritis", "Menutup pabrik-pabrik yang tidak menggunanakan bahan ramah lingkungan", "Menghimbau masyarakat untuk tidak membuang sampah rumah tangga ke sungai", "Bacalah cuplikan artikel berikut!", "Gunungkidul, tribunjogja.com – kualitas lingkungan di Kabupaten Gunungkidul masih kurang, hal ini dilihat dari banyaknya lahan kritis dan masih tercemarnya sungai-sungai yang ada di wilayah Gunungkidul. Hal ini didasarkan pada penilaian Indeks Kualitas Lingkungan Hidup (IKLH) bernilai 65,19 yang masuk dalam kategori kurang. IKLH ini merupakan rerata gabungan dari nilai Indeks Pencemaran Air (IPA), Indeks Pencemaran Udara (IPU) dan Indeks Tutupan Hutan (ITH). Plt Kepala Dinas Lingkungan Hidup Kabupaten Gunungkidul, Khairudin, mengakui jika kualitas lingkungan hidup yang ada di Gunungkidul memang masih dinilai kurang.", "Berdasarkan permasalahan di atas, solusi atau upaya apakah yang dapat dilakukan agar kedepannya permasalahan lingkungan tersebut dapat diatasi…"},
            {"1", "1, 2, dan 3", "1, 2, dan 5", "1, 3, dan 4", "2, 3, dan 5", "2, 3, dan 4", "Perhatikan beberapa tindakan dibawah ini!", "1.\tMelakukan penanaman kembali hutan yang gundul\n" +
                    "2.\tMeminimalisir penggunaan kendaraan bermotor pribadi \n" +
                    "3.\tMenghilangkan kebiasaan membuang sampah di sungai\n" +
                    "4.\tMenggunakan sedotan sekali pakai\n" +
                    "5.\tMemusnahkan sampah dengan cara dibakar", "Berdasarkan beberapa tindakan di atas, tindakan manakah yang secara positif dapat meningkatkan dan memelihara lingkungan…"},
            {"1", "Menambah peralatan dan petugas pengelolahan sampah di lokasi wisata dan bank sampah disetiap desa", "Memberikan sosialisai kepada masyarakat dan wisatawan tentang pentingnya membuang sampah pada tempatnya", "Mengajak masayarakat dan wisatawan untuk membiasakan membawa kantung yang ramah lingkungan/ tidak sekali pakai", "Melarang wisatawan membawa wadah berupa kantung plastik ke lokasi wisata, untuk menghindari membuang sampah sembarangan", "Memberikan sanksi yang tegas kepada masyakarat maupun wisatawan yang membuang sampah sembarang ", "Bacalah cuplikan artikel berikut!", "Gunungkidul, Tribunjogja.com – masalah sampah masih menjadi pekerjaan rumah bagi Pemerintah Kabupaten Gunungkidul untuk dapat segera diselesaikan. Jumlah sampah terus menumpuk, tak hanya di permukiman juga di Kawasan wisata. Sementara tempat penampungan dan pengolahan sampah masih terbatas. Berdasarkan data dari TPAS Wukirsari, jumlah sampah terus meningkat, dari bulan Januari 2016 volume sampah mencapai 3.162 meter kubik. Tahun 2017 bulan Januari naik menjadi 3654 meter kubik, dan tahun 2018 bulan januari 3477 meter kubik.", "Berdasarkan permasalahan di atas tindakan apa yang secara positif dapat menyelesaikan masalah tersebut…"},
            {"1", "2, 3, dan 5", "1, 2, dan 3", "1, 3, dan 4", "1, 2, dan 5", "2, 4, dan 5 ", "Perhatikan beberapa tindakan dibawah ini! ", "1.\tMengikuti kegiatan bersih dan menjaga lingkungan\n" +
                    "2.\tMenggunakan botol minuman sekali pakai\n" +
                    "3.\tTidak mencabut charger setalah mengisi daya Hp atau Laptop\n" +
                    "4.\tMematikan kipas angin dan lampu ketika tidak digunakan lagi\n" +
                    "5.\tPenggunaan pupuk kimia secara terus menerus untuk meningkatkan hasil produksi", "Berdasarkan beberapa tindakan di atas, tindakan manakah yang secara negatif dapat menurunkan kualitas lingkungan…"},
            {"1", "Pencemaran air tersebut menyebabkan mewabahnya bakteri Escherichia coli", "Pencemaran air tersebut menyababkan ikan-ikan disungai menjadi mati ", "Pencemaran air tersebut menyebabkan tanaman di area sungai menjadi mati", "Pencemaran air tersebut menyebabkan mewabahnya bakteri Corynebacterium", "Pencemaran air tersebut menyebabkan aliran disungai menjadi menyumbat", "Bacalah cuplikan artikel berikut!", "Wonosari, Pidjar.com – Pembuangan limbah domestik maupun peternakan rumah tangga yang dibuang ke area aliran sungai menjadi faktor penyebab utama terus menurunnya kualitas air di sungai-sungai di Gunungkidul. Limbah-limbah tersebut berupa larutan deterjen, hingga kotoran hewan dan manusia. Hal tersebut tentunya tidak hanya mengotori sungai namun juga menurunkan kualitas air sungai.", "Berdasarkan permasalaha di atas, pencemaran air tersebut menyebabkan…"},
    };

    private List<Kuis> listKuis = new ArrayList<>();
    private List<Integer> arrayList = new ArrayList<>();
    private String[] hasil = new String[15];
    private int jumlah = 0;

    private PDFView pdf1, pdf2, pdf3, pdf4, pdf5, pdf6, pdf7, pdf8, pdf9, pdf10, pdf11, pdf12, pdf13, pdf14, pdf15;
    private RadioButton rbPil1a, rbPil1b, rbPil1c, rbPil1d, rbPil1e,
            rbPil2a, rbPil2b, rbPil2c, rbPil2d, rbPil2e,
            rbPil3a, rbPil3b, rbPil3c, rbPil3d, rbPil3e,
            rbPil4a, rbPil4b, rbPil4c, rbPil4d, rbPil4e,
            rbPil5a, rbPil5b, rbPil5c, rbPil5d, rbPil5e,
            rbPil6a, rbPil6b, rbPil6c, rbPil6d, rbPil6e,
            rbPil7a, rbPil7b, rbPil7c, rbPil7d, rbPil7e,
            rbPil8a, rbPil8b, rbPil8c, rbPil8d, rbPil8e,
            rbPil9a, rbPil9b, rbPil9c, rbPil9d, rbPil9e,
            rbPil10a, rbPil10b, rbPil10c, rbPil10d, rbPil10e,
            rbPil11a, rbPil11b, rbPil11c, rbPil11d, rbPil11e,
            rbPil12a, rbPil12b, rbPil12c, rbPil12d, rbPil12e,
            rbPil13a, rbPil13b, rbPil13c, rbPil13d, rbPil13e,
            rbPil14a, rbPil14b, rbPil14c, rbPil14d, rbPil14e,
            rbPil15a, rbPil15b, rbPil15c, rbPil15d, rbPil15e;

    private DatabaseReference mDatabase;

    public KuisFragment() {
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_kuis, container, false);

        Button btnSubmit = root.findViewById(R.id.btn_submit);

        mDatabase = FirebaseDatabase.getInstance().getReference("Kuis");

        final String name = ((HomeActivity) Objects.requireNonNull(getActivity())).getName();

        pdf1 = root.findViewById(R.id.pdf1);
        pdf2 = root.findViewById(R.id.pdf2);
        pdf3 = root.findViewById(R.id.pdf3);
        pdf4 = root.findViewById(R.id.pdf4);
        pdf5 = root.findViewById(R.id.pdf5);
        pdf6 = root.findViewById(R.id.pdf6);
        pdf7 = root.findViewById(R.id.pdf7);
        pdf8 = root.findViewById(R.id.pdf8);
        pdf9 = root.findViewById(R.id.pdf9);
        pdf10 = root.findViewById(R.id.pdf10);
        pdf11 = root.findViewById(R.id.pdf11);
        pdf12 = root.findViewById(R.id.pdf12);
        pdf13 = root.findViewById(R.id.pdf13);
        pdf14 = root.findViewById(R.id.pdf14);
        pdf15 = root.findViewById(R.id.pdf15);

        rbPil1a = root.findViewById(R.id.rb_pil_1_a);
        rbPil1b = root.findViewById(R.id.rb_pil_1_b);
        rbPil1c = root.findViewById(R.id.rb_pil_1_c);
        rbPil1d = root.findViewById(R.id.rb_pil_1_d);
        rbPil1e = root.findViewById(R.id.rb_pil_1_e);

        rbPil2a = root.findViewById(R.id.rb_pil_2_a);
        rbPil2b = root.findViewById(R.id.rb_pil_2_b);
        rbPil2c = root.findViewById(R.id.rb_pil_2_c);
        rbPil2d = root.findViewById(R.id.rb_pil_2_d);
        rbPil2e = root.findViewById(R.id.rb_pil_2_e);

        rbPil3a = root.findViewById(R.id.rb_pil_3_a);
        rbPil3b = root.findViewById(R.id.rb_pil_3_b);
        rbPil3c = root.findViewById(R.id.rb_pil_3_c);
        rbPil3d = root.findViewById(R.id.rb_pil_3_d);
        rbPil3e = root.findViewById(R.id.rb_pil_3_e);

        rbPil4a = root.findViewById(R.id.rb_pil_4_a);
        rbPil4b = root.findViewById(R.id.rb_pil_4_b);
        rbPil4c = root.findViewById(R.id.rb_pil_4_c);
        rbPil4d = root.findViewById(R.id.rb_pil_4_d);
        rbPil4e = root.findViewById(R.id.rb_pil_4_e);

        rbPil5a = root.findViewById(R.id.rb_pil_5_a);
        rbPil5b = root.findViewById(R.id.rb_pil_5_b);
        rbPil5c = root.findViewById(R.id.rb_pil_5_c);
        rbPil5d = root.findViewById(R.id.rb_pil_5_d);
        rbPil5e = root.findViewById(R.id.rb_pil_5_e);

        rbPil6a = root.findViewById(R.id.rb_pil_6_a);
        rbPil6b = root.findViewById(R.id.rb_pil_6_b);
        rbPil6c = root.findViewById(R.id.rb_pil_6_c);
        rbPil6d = root.findViewById(R.id.rb_pil_6_d);
        rbPil6e = root.findViewById(R.id.rb_pil_6_e);

        rbPil7a = root.findViewById(R.id.rb_pil_7_a);
        rbPil7b = root.findViewById(R.id.rb_pil_7_b);
        rbPil7c = root.findViewById(R.id.rb_pil_7_c);
        rbPil7d = root.findViewById(R.id.rb_pil_7_d);
        rbPil7e = root.findViewById(R.id.rb_pil_7_e);

        rbPil8a = root.findViewById(R.id.rb_pil_8_a);
        rbPil8b = root.findViewById(R.id.rb_pil_8_b);
        rbPil8c = root.findViewById(R.id.rb_pil_8_c);
        rbPil8d = root.findViewById(R.id.rb_pil_8_d);
        rbPil8e = root.findViewById(R.id.rb_pil_8_e);

        rbPil9a = root.findViewById(R.id.rb_pil_9_a);
        rbPil9b = root.findViewById(R.id.rb_pil_9_b);
        rbPil9c = root.findViewById(R.id.rb_pil_9_c);
        rbPil9d = root.findViewById(R.id.rb_pil_9_d);
        rbPil9e = root.findViewById(R.id.rb_pil_9_e);

        rbPil10a = root.findViewById(R.id.rb_pil_10_a);
        rbPil10b = root.findViewById(R.id.rb_pil_10_b);
        rbPil10c = root.findViewById(R.id.rb_pil_10_c);
        rbPil10d = root.findViewById(R.id.rb_pil_10_d);
        rbPil10e = root.findViewById(R.id.rb_pil_10_e);

        rbPil11a = root.findViewById(R.id.rb_pil_11_a);
        rbPil11b = root.findViewById(R.id.rb_pil_11_b);
        rbPil11c = root.findViewById(R.id.rb_pil_11_c);
        rbPil11d = root.findViewById(R.id.rb_pil_11_d);
        rbPil11e = root.findViewById(R.id.rb_pil_11_e);

        rbPil12a = root.findViewById(R.id.rb_pil_12_a);
        rbPil12b = root.findViewById(R.id.rb_pil_12_b);
        rbPil12c = root.findViewById(R.id.rb_pil_12_c);
        rbPil12d = root.findViewById(R.id.rb_pil_12_d);
        rbPil12e = root.findViewById(R.id.rb_pil_12_e);

        rbPil13a = root.findViewById(R.id.rb_pil_13_a);
        rbPil13b = root.findViewById(R.id.rb_pil_13_b);
        rbPil13c = root.findViewById(R.id.rb_pil_13_c);
        rbPil13d = root.findViewById(R.id.rb_pil_13_d);
        rbPil13e = root.findViewById(R.id.rb_pil_13_e);

        rbPil14a = root.findViewById(R.id.rb_pil_14_a);
        rbPil14b = root.findViewById(R.id.rb_pil_14_b);
        rbPil14c = root.findViewById(R.id.rb_pil_14_c);
        rbPil14d = root.findViewById(R.id.rb_pil_14_d);
        rbPil14e = root.findViewById(R.id.rb_pil_14_e);

        rbPil15a = root.findViewById(R.id.rb_pil_15_a);
        rbPil15b = root.findViewById(R.id.rb_pil_15_b);
        rbPil15c = root.findViewById(R.id.rb_pil_15_c);
        rbPil15d = root.findViewById(R.id.rb_pil_15_d);
        rbPil15e = root.findViewById(R.id.rb_pil_15_e);

        dataSoal();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DecimalFormat df = new DecimalFormat("#.##");
                Utils.showDialog(requireContext());
                HashMap<String, String> maps = new HashMap<>();
                maps.put("user", name);
                maps.put("nilai", df.format(nilai()));
                mDatabase.push().setValue(maps).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(requireContext(), "Berhasil mengirim data", Toast.LENGTH_SHORT).show();
                        Utils.hideDialog();
                        showResult(((HomeActivity) Objects.requireNonNull(getActivity())).getName(),df.format(nilai()));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        Utils.hideDialog();
                    }
                });

            }
        });
        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void showResult(String nama,String hasil){
        final Dialog dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.item_rv_quis);
        dialog.setCancelable(false);

        final WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(Objects.requireNonNull(dialog.getWindow()).getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        TextView name = dialog.findViewById(R.id.tv_nama);
        TextView nilai = dialog.findViewById(R.id.tv_nilai);
        Button ok = dialog.findViewById(R.id.btn_ok);

        name.setText(nama);
        nilai.setText(hasil);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                ((HomeActivity) Objects.requireNonNull(getActivity())).goHomePage();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }
    private void dataSoal() {
        pdf1.fromAsset("kuis_1.pdf")
                .spacing(0)
                .pageFitPolicy(FitPolicy.WIDTH)
                .load();
        pdf2.fromAsset("kuis_2.pdf")
                .spacing(0)
                .pageFitPolicy(FitPolicy.WIDTH)
                .load();
        pdf3.fromAsset("kuis_3.pdf")
                .spacing(0)
                .pageFitPolicy(FitPolicy.WIDTH)
                .load();
        pdf4.fromAsset("kuis_4.pdf")
                .spacing(0)
                .pageFitPolicy(FitPolicy.WIDTH)
                .load();
        pdf5.fromAsset("kuis_5.pdf")
                .spacing(0)
                .pageFitPolicy(FitPolicy.WIDTH)
                .load();
        pdf6.fromAsset("kuis_6.pdf")
                .spacing(0)
                .pageFitPolicy(FitPolicy.WIDTH)
                .load();
        pdf7.fromAsset("kuis_7.pdf")
                .spacing(0)
                .pageFitPolicy(FitPolicy.WIDTH)
                .load();
        pdf8.fromAsset("kuis_8.pdf")
                .spacing(0)
                .pageFitPolicy(FitPolicy.WIDTH)
                .load();
        pdf9.fromAsset("kuis_9.pdf")
                .spacing(0)
                .pageFitPolicy(FitPolicy.WIDTH)
                .load();
        pdf10.fromAsset("kuis_10.pdf")
                .spacing(0)
                .pageFitPolicy(FitPolicy.WIDTH)
                .load();
        pdf11.fromAsset("kuis_11.pdf")
                .spacing(0)
                .pageFitPolicy(FitPolicy.WIDTH)
                .load();
        pdf12.fromAsset("kuis_12.pdf")
                .spacing(0)
                .pageFitPolicy(FitPolicy.WIDTH)
                .load();
        pdf13.fromAsset("kuis_13.pdf")
                .spacing(0)
                .pageFitPolicy(FitPolicy.WIDTH)
                .load();
        pdf14.fromAsset("kuis_14.pdf")
                .spacing(0)
                .pageFitPolicy(FitPolicy.WIDTH)
                .load();
        pdf15.fromAsset("kuis_15.pdf")
                .spacing(0)
                .pageFitPolicy(FitPolicy.WIDTH)
                .load();
        dataPilihan();
    }

    private void dataPilihan() {
        rbPil1a.setText(getResources().getText(R.string._1a));
        rbPil1b.setText(getResources().getText(R.string._1b));
        rbPil1c.setText(getResources().getText(R.string._1c));
        rbPil1d.setText(getResources().getText(R.string._1d));
        rbPil1e.setText(getResources().getText(R.string._1e));

        rbPil2a.setText(getResources().getText(R.string._2a));
        rbPil2b.setText(getResources().getText(R.string._2b));
        rbPil2c.setText(getResources().getText(R.string._2c));
        rbPil2d.setText(getResources().getText(R.string._2d));
        rbPil2e.setText(getResources().getText(R.string._2e));

        rbPil3a.setText(getResources().getText(R.string._3a));
        rbPil3b.setText(getResources().getText(R.string._3b));
        rbPil3c.setText(getResources().getText(R.string._3c));
        rbPil3d.setText(getResources().getText(R.string._3d));
        rbPil3e.setText(getResources().getText(R.string._3e));

        rbPil4a.setText(getResources().getText(R.string._4a));
        rbPil4b.setText(getResources().getText(R.string._4b));
        rbPil4c.setText(getResources().getText(R.string._4c));
        rbPil4d.setText(getResources().getText(R.string._4d));
        rbPil4e.setText(getResources().getText(R.string._4e));

        rbPil5a.setText(getResources().getText(R.string._5a));
        rbPil5b.setText(getResources().getText(R.string._5b));
        rbPil5c.setText(getResources().getText(R.string._5c));
        rbPil5d.setText(getResources().getText(R.string._5d));
        rbPil5e.setText(getResources().getText(R.string._5e));

        rbPil6a.setText(getResources().getText(R.string._6a));
        rbPil6b.setText(getResources().getText(R.string._6b));
        rbPil6c.setText(getResources().getText(R.string._6c));
        rbPil6d.setText(getResources().getText(R.string._6d));
        rbPil6e.setText(getResources().getText(R.string._6e));

        rbPil7a.setText(getResources().getText(R.string._7a));
        rbPil7b.setText(getResources().getText(R.string._7b));
        rbPil7c.setText(getResources().getText(R.string._7c));
        rbPil7d.setText(getResources().getText(R.string._7d));
        rbPil7e.setText(getResources().getText(R.string._7e));

        rbPil8a.setText(getResources().getText(R.string._8a));
        rbPil8b.setText(getResources().getText(R.string._8b));
        rbPil8c.setText(getResources().getText(R.string._8c));
        rbPil8d.setText(getResources().getText(R.string._8d));
        rbPil8e.setText(getResources().getText(R.string._8e));

        rbPil9a.setText(getResources().getText(R.string._9a));
        rbPil9b.setText(getResources().getText(R.string._9b));
        rbPil9c.setText(getResources().getText(R.string._9c));
        rbPil9d.setText(getResources().getText(R.string._9d));
        rbPil9e.setText(getResources().getText(R.string._9e));

        rbPil10a.setText(getResources().getText(R.string._10a));
        rbPil10b.setText(getResources().getText(R.string._10b));
        rbPil10c.setText(getResources().getText(R.string._10c));
        rbPil10d.setText(getResources().getText(R.string._10d));
        rbPil10e.setText(getResources().getText(R.string._10e));

        rbPil11a.setText(getResources().getText(R.string._11a));
        rbPil11b.setText(getResources().getText(R.string._11b));
        rbPil11c.setText(getResources().getText(R.string._11c));
        rbPil11d.setText(getResources().getText(R.string._11d));
        rbPil11e.setText(getResources().getText(R.string._11e));

        rbPil12a.setText(getResources().getText(R.string._12a));
        rbPil12b.setText(getResources().getText(R.string._12b));
        rbPil12c.setText(getResources().getText(R.string._12c));
        rbPil12d.setText(getResources().getText(R.string._12d));
        rbPil12e.setText(getResources().getText(R.string._12e));

        rbPil13a.setText(getResources().getText(R.string._13a));
        rbPil13b.setText(getResources().getText(R.string._13b));
        rbPil13c.setText(getResources().getText(R.string._13c));
        rbPil13d.setText(getResources().getText(R.string._13d));
        rbPil13e.setText(getResources().getText(R.string._13e));

        rbPil14a.setText(getResources().getText(R.string._14a));
        rbPil14b.setText(getResources().getText(R.string._14b));
        rbPil14c.setText(getResources().getText(R.string._14c));
        rbPil14d.setText(getResources().getText(R.string._14d));
        rbPil14e.setText(getResources().getText(R.string._14e));

        rbPil15a.setText(getResources().getText(R.string._15a));
        rbPil15b.setText(getResources().getText(R.string._15b));
        rbPil15c.setText(getResources().getText(R.string._15c));
        rbPil15d.setText(getResources().getText(R.string._15d));
        rbPil15e.setText(getResources().getText(R.string._15e));
    }

    private double nilai() {
        double score;
        double nilai = 0;
        if (rbPil1a.isChecked()) nilai++;
        if (rbPil2d.isChecked()) nilai++;
        if (rbPil3e.isChecked()) nilai++;
        if (rbPil4b.isChecked()) nilai++;
        if (rbPil5c.isChecked()) nilai++;
        if (rbPil6e.isChecked()) nilai++;
        if (rbPil7a.isChecked()) nilai++;
        if (rbPil8e.isChecked()) nilai++;
        if (rbPil9b.isChecked()) nilai++;
        if (rbPil10b.isChecked()) nilai++;
        if (rbPil11d.isChecked()) nilai++;
        if (rbPil12c.isChecked()) nilai++;
        if (rbPil13c.isChecked()) nilai++;
        if (rbPil14d.isChecked()) nilai++;
        if (rbPil15a.isChecked()) nilai++;

        score = (nilai * 20) / 3;

        return score;
    }
}