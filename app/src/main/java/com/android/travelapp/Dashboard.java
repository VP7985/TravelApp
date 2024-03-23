package com.android.travelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
    TextView txtNama, txtEmail;
    Button checkTickets;

    AlertDialog alertDialog;
    MenuInflater inflater;

    private ArrayList<String> al_img_tour = new ArrayList<>();
    private ArrayList<String> al_name_tour = new ArrayList<>();
    private ArrayList<String> al_desc_tour = new ArrayList<>();
    private ArrayList<Integer> al_price_tour = new ArrayList<>();
    private ArrayList<String> al_location = new ArrayList<>();

    SharedPreferences preferences;

    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_TOTAL_PRICE = "total_price";
    private static final String KEY_NAME_TOUR = "name_tour";
    private static final String KEY_COUNT_ITEMS = "count_items";

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        txtNama = findViewById(R.id.tv_fullname);
        txtEmail = findViewById(R.id.tv_email);
        checkTickets = findViewById(R.id.check_ticket);

        checkTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameView = preferences.getString(KEY_NAME, null);
                String emailView = preferences.getString(KEY_EMAIL, null);
                String phoneView = preferences.getString(KEY_PHONE, null);

                String nameTourView = preferences.getString(KEY_NAME_TOUR, null);
                String totalItemsView = preferences.getString(KEY_COUNT_ITEMS, null);
                String totalPriceView = preferences.getString(KEY_TOTAL_PRICE, null);

                if (nameView == "" || emailView == "" || phoneView == "" || nameTourView == "" || totalItemsView == "" || totalPriceView == "" ||
                        nameView == null || emailView == null || phoneView == null || nameTourView == null || totalItemsView == null || totalPriceView == null) {
                    AlertDialog dialog = new AlertDialog.Builder(Dashboard.this)
                            .setTitle("Check Tickets")
                            .setMessage("\nData is Empty")
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(Dashboard.this, Dashboard.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }).show();
                }else if (nameView == nameView || emailView == emailView || phoneView == phoneView || nameTourView == nameTourView || totalItemsView == totalItemsView || totalPriceView == totalPriceView){
                    Intent intent = new Intent(Dashboard.this, Tickets.class);
                    startActivity(intent);
                }
            }
        });

        preferences = getSharedPreferences("userInfo", 0);

        String namaView = preferences.getString(KEY_NAME, null);
        String emailView = preferences.getString(KEY_EMAIL, null);

        if (namaView != null || emailView != null){
            txtNama.setText(namaView);
            txtEmail.setText(emailView);
        }

        getData();

        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
    }

/*    private void getData() {
        //Pantai Pandawa
        al_img_tour.add("https://www.rentalmobilbali.net/wp-content/uploads/2016/05/Pantai-Pandawa-Kutuh.jpg");
        al_name_tour.add("Pantai Pandawa");
        al_desc_tour.add("Pantai Pandawa berlokasi di Bali Selatan, tepatnya di Desa Kutuh, Kecamatan Kuta Selatan, Kabupaten Badung, Bali. Dahulu pantai ini dikenal sebagai Secret Beach karena lokasinya yang berada di belakang tebing-tebing tinggi ditumbuhi oleh semak belukar.");
        al_price_tour.add(8000);
        al_location.add("Pantai Pandawa, Bali");

        //Pantai Kuta
        al_img_tour.add("https://www.rentalmobilbali.net/wp-content/uploads/2016/05/Objek-Wisata-Kuta-Bali.jpg");
        al_name_tour.add("Pantai Kuta");
        al_desc_tour.add("Pantai Kuta terletak di kelurahan Kuta, Kabupaten Badung, menjadi objek wisata alam pantai yang paling menarik dan indah di pulau Dewata Bali, tempat rekreasi alam pesisir ini melengkapi destinasi tour populer di kawasan pariwisata Bali Selatan yang memang populer dengan wilayah pesisir yang memiliki pasir putih.");
        al_price_tour.add(5000);
        al_location.add("Pantau Kuta, Bali");

        //Tanah Lot
        al_img_tour.add("https://www.rentalmobilbali.net/wp-content/uploads/2016/05/Tanah-Lot-Tabanan-Bali.jpg");
        al_name_tour.add("Tanah Lot");
        al_desc_tour.add("Tanah Lot berada di Desa Beraban, Kecamatan Kediri, Kabupaten Tabanan. Jaraknya sekitar 13 km ke arah barat kota Tabanan. Dari bandara udara Ngurah Rai, menuju lokasi pura dapat di tempuh dalam waktu kurang lebih 1 jam 20 menit.");
        al_price_tour.add(20000);
        al_location.add("Tanah Lot, Bali");


        //Pura Uluwatu
        al_img_tour.add("https://www.rentalmobilbali.net/wp-content/uploads/2016/03/Sunset-Pura-Uluwatu.jpg");
        al_name_tour.add("Pura Uluwatu");
        al_desc_tour.add("Lokasi pura Luhur Uluwatu tepatnya berada di desa Pecatu, Kecamatan Kuta Selatan, Kabupaten Badung, Bali. Jarak yang di tempuh dari Bandara Ngurah Rai menuju lokasi pura Luhur Uluwatu Bali, sekitar 1 jam ke arah selatan dengan kendaraan pribadi.");
        al_price_tour.add(30000);
        al_location.add("Pura Uluwatu, Bali");

        //Tanjung Benoa
        al_img_tour.add("https://www.rentalmobilbali.net/wp-content/uploads/2013/11/tanjung-benoa-watersport.jpg");
        al_name_tour.add("Tanjung Benoa");
        al_desc_tour.add("Lokasi pantai Tanjung Benoa ada di kawasan Bali selatan, berdekatan dengan kawasan wisata Nusa Dua Bali. Alamatnya adalah Kelurahan Tanjung Benoa, Kecamatan Kuta Selatan, Kabupaten Badung.");
        al_price_tour.add(5000);
        al_location.add("Tanjung Benoa, Bali");

        //Pura Ulun Danu
        al_img_tour.add("https://www.rentalmobilbali.net/wp-content/uploads/2016/05/Objek-Wisata-Pura-Ulun-Danu-Bedugul.jpg");
        al_name_tour.add("Pura Ulun Danu");
        al_desc_tour.add("Lokasi pura Ulun Danu Beratan berada di sisi barat Danau Bedugul. Alamatnya berada di jalan Raya Bedugul, Candi Kuning, Kecamatan Baturiti Kabupaten Tabanan. Apabila anda memilih menginap di salah satu hotel yang berada di tepi pantai Kuta, maka akan memerlukan waktu tempuh sekitar 2 jam perjalanan untuk sampai di kawasan tempat wisata Bedugul Tabanan.");
        al_price_tour.add(30000);
        al_location.add("Pura Ulun Danu, Bali");

        //Pantai Parai Tenggiri
        al_img_tour.add("https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2020/06/Pantai-Parai-Tenggiri.jpg");
        al_name_tour.add("Pantai Parai Tenggiri");
        al_desc_tour.add("Pantai Parai Tenggiri terletak tepat di sebelah Pantai Matras yaitu berlokasi Sungailiat, Bangka, Kepulauan Bangka Belitung. Sama seperti Pantai Matras yang terkenal dengan keindahannya, Pantai Parai Tenggiri juga menawarkan keelokan tersendiri.");
        al_price_tour.add(30000);
        al_location.add("Pantai Parai Tenggiri, Bangka Belitung");

        //Nusa Dua
        al_img_tour.add("https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2020/06/Nusa-Dua-Bali.jpg");
        al_name_tour.add("Nusa Dua (Bali)");
        al_desc_tour.add("Lokasi Nusa Dua tempat wisata Bali, terletak di paling ujung bagian tenggara pulau Bali. Nusa Dua berjarak sekitar 40 kilometer dari kota Denpasar. Jika dari dari Bandara Internasional Ngurah Rai, jaraknya kurang lebih 8 kilometer, atau sekitar tiga puluh menit perjalanan dengan menggunakan mobil.");
        al_price_tour.add(5000);
        al_location.add("Nusa Dua, Bali");

        //Gunung Rinjani
        al_img_tour.add("https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2020/06/gunung-rinjani.jpg");
        al_name_tour.add("Gunung Rinjani");
        al_desc_tour.add("Gunung Rinjani adalah gunung yang berlokasi di Pulau Lombok, Nusa Tenggara Barat. Gunung yang merupakan gunung berapi kedua tertinggi di Indonesia dengan ketinggian 3.726 mdpl serta terletak pada lintang 8º25' LS dan 116º28' BT ini merupakan gunung favorit bagi pendaki Indonesia karena keindahan pemandangannya.");
        al_price_tour.add(7500);
        al_location.add("Gunung Rinjani, Lombok, NTB");

        //Danau Toba
        al_img_tour.add("https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/danau-toba.jpg");
        al_name_tour.add("Danau Toba");
        al_desc_tour.add("Alamat lokasi Danau Toba berada di Sumatera Utara, dan dikelilingi oleh 7 kabupaten (Simalungun, Toba Samosir, Tapanuli Utara, Humbang Hasundutan, Dairi, Karo, dan Samosir). Untuk mencapai kawasan ini sobat native harus masuk ke pintu masuknya terlebih dahulu yang berada di Medan. Menuju ke Kota Medan bisa menggunakan pesawat atau dengan menggunakan bus.");
        al_price_tour.add(5000);
        al_location.add("Danau Toba, Sumatra Utara");

        //Nusa Penida
        al_img_tour.add("https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/nusa-penida.jpg");
        al_name_tour.add("Nusa Penida");
        al_desc_tour.add("Nusa Penida adalah sebuah pulau kecil yang berada sebelah tenggara pulau Bali, dan terpisah oleh selat Badung. Di dekat pulau ini, terdapat 2 pulau kecil lain, yaitu pulau Nusa Lembongan dan pulau Nusa Ceningan. Ketiga pulau kecil yang ada di bagian tenggara pulau Bali, memiliki keunikan dan daya tarik tersendiri dan ada perbedaan satu sama lain. Ketiga pulau masuk dalam wilayah pemerintahan kabupaten Klungkung, provinsi Bali.");
        al_price_tour.add(10000);
        al_location.add("Nusa Penida, Bali");

        //Taman Laut Bunaken
        al_img_tour.add("https://www.itrip.id/wp-content/uploads/2020/10/Taman-Nasional-Bunaken-Surga-Bawah-Laut.jpg");
        al_name_tour.add("Taman Laut Bunaken");
        al_desc_tour.add("Taman Nasional Bunaken adalah taman laut yang terletak di Sulawesi Utara, Indonesia. Taman ini terletak di Segitiga Terumbu Karang yang menjadi habitat bagi 390 spesies terumbu karang[2] dan juga berbagai spesies ikan, moluska, reptil, dan mamalia laut. Taman Nasional Bunaken merupakan perwakilan ekosistem laut Indonesia, meliputi padang rumput laut, terumbu karang, dan ekosistem pantai.");
        al_price_tour.add(25000);
        al_location.add("Taman Laut Bunaken, Sulawesi Utara");

        //Wakatobi
        al_img_tour.add("https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/wakatobi.jpg");
        al_name_tour.add("Wakatobi");
        al_desc_tour.add("Taman Nasional Wakatobi adalah salah satu taman nasional di Indonesia. Letaknya di Kabupaten Wakatobi, Sulawesi Tenggara.");
        al_price_tour.add(160000);
        al_location.add("Wakatobi, Sulawesi Tenggara");

        //Kepulauan Raja Ampat (Papua Barat)
        al_img_tour.add("https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/raja-ampat.jpg");
        al_name_tour.add("Kepulauan Raja Ampat (Papua Barat)");
        al_desc_tour.add("Kabupaten Raja Ampat adalah salah satu kabupaten di provinsi Papua Barat, Indonesia. Ibukota kabupaten ini terletak di Waisai. Kabupaten ini memiliki 610 pulau, termasuk kepulauan Raja Ampat. Empat di antaranya, yakni Pulau Misool, Salawati, Batanta dan Waigeo, merupakan pulau-pulau besar. Dari seluruh pulau hanya 35 pulau yang berpenghuni sedangkan pulau lainnya tidak berpenghuni dan sebagian besar belum memiliki nama. Kabupaten ini memiliki total luas 67.379,60 km² dengan rincian luas daratan 7.559,60 km² dan luas lautan 59.820,00 km².");
        al_price_tour.add(500000);
        al_location.add("Kepulauan Raja Ampat, Papua Barat");

        //Gunung Bromo (Jawa Timur)
        al_img_tour.add("https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/gunung-bromo.jpg");
        al_name_tour.add("Gunung Bromo (Jawa Timur)");
        al_desc_tour.add("Gunung Bromo berlokasi dan terletak di empat kabupaten pemerintahan Provinsi Jawa Timur. Yaitu di antara Kaputen Malang , Kabupaten Pasuruan, Kabupaten Probolinggo dan Kapupaten Lumajang.");
        al_price_tour.add(34000);
        al_location.add("Gunung Bromo,Jawa Timur");

        //Pulau Komodo (Nusa Tenggara Timur)
        al_img_tour.add("https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/pulau-komodo.jpg");
        al_name_tour.add("Pulau Komodo (NTT)");
        al_desc_tour.add("Pulau Komodo adalah sebuah pulau yang terletak di Kepulauan Nusa Tenggara, berada di sebelah timur Pulau Sumbawa, yang dipisahkan oleh Selat Sape. Pulau Komodo dikenal sebagai habitat asli hewan komodo.");
        al_price_tour.add(5000);
        al_location.add("Pulau Komodo, Nusa Tenggara Timur");

        //Candi Borobudur (Jawa Tengah)
        al_img_tour.add("https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/candi-borobudur.jpg");
        al_name_tour.add("Candi Borobudur (Jawa Tengah)");
        al_desc_tour.add("Lokasi Borobudur berada di Magelang, Provinsi Jawa Tengah. Objek wisata ini berada sekitar 100 km dari Semarang, 86 km dari Surakarta, dan 40 km dari DI Yogyakarta. Dan karena lebih dekat dari Yogyakarta dan lebih sering pula dijadikan agenda obyek wisata dari kegiatan promosi pariwisata dari Yogyakarta yang lebih intensif, sebagian orang Indonesia mengira Candi Borobudur ini berada di Daerah Istimewa Yogyakarta.");
        al_price_tour.add(50000);
        al_location.add("Candi Borobudur, Jawa Tengah");

        //Tana Toraja (Sulawesi Selatan)
        al_img_tour.add("https://blog.reservasi.com/wp-content/uploads/2016/06/tempat-wisata-di-tana-toraja-1.jpg");
        al_name_tour.add("Tana Toraja (Sulawesi Selatan)");
        al_desc_tour.add("Kabupaten Tana Toraja adalah salah satu kabupaten yang berada di provinsi Sulawesi Selatan, Indonesia. Ibu kota dari kabupaten ini ada di kecamatan Makale. Tana Toraja memiliki luas wilayah 2.054,30 km² dan pada tahun 2021 memiliki penduduk sebanyak 270.489 jiwa dengan kepadatan 132 jiwa/km².");
        al_price_tour.add(10000);
        al_location.add("Tana Toraja, Sulawesi Selatan");

        //Danau Kelimutu (NTT)
        al_img_tour.add("https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/danau-kelimutu.jpg");
        al_name_tour.add("Danau Kelimutu (NTT)");
        al_desc_tour.add("Tiwu atau Danau Kelimutu di bagi atas tiga bagian yang sesuai dengan warna – warna air danau yang terdapat didalamnya. Danau Kelimutu terdapat di provinsi? Tepatnya Pulau Flores Provinsi Nusa Tenggara Timur. Gunung berapi kelimutu dengan ketinggian 5.377 kaki tinggi menjulang berdiri di Desa Pemo Kabupaten Ende mempunyai kisah mistery yang akan dibahas dalam artikel ini.");
        al_price_tour.add(5000);
        al_location.add("Danau Kelimutu, NTT");

        //Kawah Ijen (Jawa Timur)
        al_img_tour.add("https://royaltour.web.id/wp-content/uploads/2017/10/paket-wisata-kawah-ijen-2-hari-1-malam.jpg");
        al_name_tour.add("Kawah Ijen (Jawa Timur)");
        al_desc_tour.add("Mengenai letak dan lokasi Kawah Ijen, ada yang menyebutnya Kawah Ijen Banyuwangi, ada pula yang menyebutnya Kawah Ijen Bondowoso, wajar saja, karna wisata gunung Ijen merupakan bagian dari Taman Nasional Baluran yang terletak diperbatasan 2 daerah, yaitu wilayah Kecamatan Licin, Kabupaten Banyuwangi dan Kecamatan Klobang, Kabupaten Bondowoso, jadi identik dengan wisata Banyuwangi dan Bondowoso.");
        al_price_tour.add(7500);
        al_location.add("Kawah Ijen, Jawa Timur");

        RecycleViewAdapterProcess();

    }*/
private void getData() {
        //Pandawa Beach
        al_img_tour.add("https://www.rentalmobilbali.net/wp-content/uploads/2016/05/Pantai-Pandawa-Kutuh.jpg");
        al_name_tour.add("Pandawa Beach");
        al_desc_tour.add("Pandawa Beach is located in South Bali, precisely in Kutuh Village, South Kuta District, Badung Regency, Bali. This beach was formerly known as Secret Beach because of its location behind high cliffs covered with shrubs.");
        al_price_tour.add(8000);
        al_location.add("Pandawa Beach, Bali");

        //Kuta Beach
        al_img_tour.add("https://www.rentalmobilbali.net/wp-content/uploads/2016/05/Objek-Wisata-Kuta-Bali.jpg");
        al_name_tour.add("Kuta Beach");
        al_desc_tour.add("Kuta Beach is located in the village of Kuta, Badung Regency, and is the most attractive and beautiful natural beach tourist attraction in the island of Bali, a coastal recreational area that complements popular tour destinations in the South Bali tourism area, which is famous for its coastal areas with white sand.");
        al_price_tour.add(5000);
        al_location.add("Kuta Beach, Bali");

        //Tanah Lot
        al_img_tour.add("https://www.rentalmobilbali.net/wp-content/uploads/2016/05/Tanah-Lot-Tabanan-Bali.jpg");
        al_name_tour.add("Tanah Lot");
        al_desc_tour.add("Tanah Lot is located in Beraban Village, Kediri District, Tabanan Regency. It is approximately 13 km west of Tabanan city. From Ngurah Rai Airport, the journey to the temple location takes approximately 1 hour and 20 minutes.");
        al_price_tour.add(20000);
        al_location.add("Tanah Lot, Bali");


        //Uluwatu Temple
        al_img_tour.add("https://www.rentalmobilbali.net/wp-content/uploads/2016/03/Sunset-Pura-Uluwatu.jpg");
        al_name_tour.add("Uluwatu Temple");
        al_desc_tour.add("The location of Uluwatu Temple is in Pecatu Village, South Kuta District, Badung Regency, Bali. The distance from Ngurah Rai Airport to Uluwatu Temple Bali is about 1 hour south by private vehicle.");
        al_price_tour.add(30000);
        al_location.add("Uluwatu Temple, Bali");

        //Tanjung Benoa
        al_img_tour.add("https://www.rentalmobilbali.net/wp-content/uploads/2013/11/tanjung-benoa-watersport.jpg");
        al_name_tour.add("Tanjung Benoa");
        al_desc_tour.add("Tanjung Benoa Beach is located in the southern part of Bali, near the Nusa Dua Bali tourism area. Its address is in Tanjung Benoa Village, South Kuta District, Badung Regency.");
        al_price_tour.add(5000);
        al_location.add("Tanjung Benoa, Bali");

        //Ulun Danu Temple
        al_img_tour.add("https://www.rentalmobilbali.net/wp-content/uploads/2016/05/Objek-Wisata-Pura-Ulun-Danu-Bedugul.jpg");
        al_name_tour.add("Ulun Danu Temple");
        al_desc_tour.add("The location of Ulun Danu Beratan Temple is on the west side of Lake Beratan. Its address is on Jalan Raya Bedugul, Candi Kuning, Baturiti District, Tabanan Regency. If you choose to stay in one of the hotels on Kuta Beach, it will take about 2 hours to reach the Bedugul Tabanan tourist area.");
        al_price_tour.add(30000);
        al_location.add("Ulun Danu Temple, Bali");

        //Parai Tenggiri Beach
        al_img_tour.add("https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2020/06/Pantai-Parai-Tenggiri.jpg");
        al_name_tour.add("Parai Tenggiri Beach");
        al_desc_tour.add("Parai Tenggiri Beach is located right next to Matras Beach, which is located in Sungailiat, Bangka, Bangka Belitung Islands. Like Matras Beach, which is famous for its beauty, Parai Tenggiri Beach also offers its own beauty.");
        al_price_tour.add(30000);
        al_location.add("Parai Tenggiri Beach, Bangka Belitung");

        //Nusa Dua
        al_img_tour.add("https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2020/06/Nusa-Dua-Bali.jpg");
        al_name_tour.add("Nusa Dua (Bali)");
        al_desc_tour.add("Nusa Dua tourist location in Bali is located at the southeastern tip of the island of Bali. Nusa Dua is about 40 kilometers from Denpasar city. If from Ngurah Rai International Airport, the distance is approximately 8 kilometers, or about thirty minutes by car.");
        al_price_tour.add(5000);
        al_location.add("Nusa Dua, Bali");

        //Mount Rinjani
        al_img_tour.add("https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2020/06/gunung-rinjani.jpg");
        al_name_tour.add("Mount Rinjani");
        al_desc_tour.add("Mount Rinjani is a mountain located on Lombok Island, West Nusa Tenggara. The mountain, which is the second highest volcano in Indonesia with a height of 3,726 meters above sea level and located at latitude 8º25' LS and 116º28' BT, is a favorite mountain for Indonesian climbers because of its beautiful scenery.");
        al_price_tour.add(7500);
        al_location.add("Mount Rinjani, Lombok, NTB");

        //Lake Toba
        al_img_tour.add("https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/danau-toba.jpg");
        al_name_tour.add("Lake Toba");
        al_desc_tour.add("The location of Lake Toba is in North Sumatra, and is surrounded by 7 regencies (Simalungun, Toba Samosir, North Tapanuli, Humbang Hasundutan, Dairi, Karo, and Samosir). To reach this area, you must enter its entrance gate, which is located in Medan. To get to Medan City, you can use a plane or bus.");
        al_price_tour.add(5000);
        al_location.add("Lake Toba, North Sumatra");

        //Nusa Penida
        al_img_tour.add("https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/nusa-penida.jpg");
        al_name_tour.add("Nusa Penida");
        al_desc_tour.add("Nusa Penida is a small island located southeast of Bali Island, separated by the Badung Strait. Near this island, there are 2 other small islands, namely Nusa Lembongan and Nusa Ceningan. These three small islands in the southeastern part of Bali Island have their own uniqueness and attractions. All three islands are within the jurisdiction of Klungkung regency, Bali province.");
        al_price_tour.add(10000);
        al_location.add("Nusa Penida, Bali");

        //Bunaken Marine Park
        al_img_tour.add("https://www.itrip.id/wp-content/uploads/2020/10/Taman-Nasional-Bunaken-Surga-Bawah-Laut.jpg");
        al_name_tour.add("Bunaken Marine Park");
        al_desc_tour.add("Bunaken National Park is a marine park located in North Sulawesi, Indonesia. The park is located in the Coral Triangle which is the habitat for 390 species of coral reefs and various species of fish, mollusks, reptiles, and marine mammals. Bunaken National Park represents Indonesia's marine ecosystem, including seagrass beds, coral reefs, and coastal ecosystems.");
        al_price_tour.add(25000);
        al_location.add("Bunaken Marine Park, North Sulawesi");

        //Wakatobi
        al_img_tour.add("https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/wakatobi.jpg");
        al_name_tour.add("Wakatobi");
        al_desc_tour.add("Wakatobi National Park is one of the national parks in Indonesia. It is located in Wakatobi Regency, Southeast Sulawesi.");
        al_price_tour.add(160000);
        al_location.add("Wakatobi, Southeast Sulawesi");

        //Raja Ampat Islands (West Papua)
        al_img_tour.add("https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/raja-ampat.jpg");
        al_name_tour.add("Raja Ampat Islands (West Papua)");
        al_desc_tour.add("Raja Ampat Regency is one of the regencies in West Papua province, Indonesia. The capital of this regency is located in Waisai. This regency has 610 islands, including the Raja Ampat Islands. Four of them, namely Misool Island, Salawati, Batanta, and Waigeo, are large islands. Of all the islands, only 35 are inhabited while the others are uninhabited and most of them do not yet have names. This regency has a total area of 67,379.60 km² with details of land area of 7,559.60 km² and sea area of 59,820.00 km².");
        al_price_tour.add(500000);
        al_location.add("Raja Ampat Islands, West Papua");

        //Mount Bromo (East Java)
        al_img_tour.add("https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/gunung-bromo.jpg");
        al_name_tour.add("Mount Bromo (East Java)");
        al_desc_tour.add("Mount Bromo is located in four regencies in East Java Province. Namely in the Kaputen Malang, Pasuruan Regency, Probolinggo Regency and Lumajang Regency.");
        al_price_tour.add(34000);
        al_location.add("Mount Bromo, East Java");

        //Komodo Island (East Nusa Tenggara)
        al_img_tour.add("https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/pulau-komodo.jpg");
        al_name_tour.add("Komodo Island (East Nusa Tenggara)");
        al_desc_tour.add("Komodo Island is an island located in the Nusa Tenggara Islands, located to the east of Sumbawa Island, separated by the Sape Strait. Komodo Island is known as the original habitat of Komodo dragons.");
        al_price_tour.add(5000);
        al_location.add("Komodo Island, East Nusa Tenggara");

        //Borobudur Temple (Central Java)
        al_img_tour.add("https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/candi-borobudur.jpg");
        al_name_tour.add("Borobudur Temple (Central Java)");
        al_desc_tour.add("The location of Borobudur is in Magelang, Central Java Province. This tourist attraction is about 100 km from Semarang, 86 km from Surakarta, and 40 km from Yogyakarta. And because it is closer to Yogyakarta and is more often used as a tourist attraction in the tourism promotion activities of Yogyakarta which are more intensive, some Indonesians mistakenly think that Borobudur Temple is located in the Special Region of Yogyakarta.");
        al_price_tour.add(50000);
        al_location.add("Borobudur Temple, Central Java");

        //Tana Toraja (South Sulawesi)
        al_img_tour.add("https://blog.reservasi.com/wp-content/uploads/2016/06/tempat-wisata-di-tana-toraja-1.jpg");
        al_name_tour.add("Tana Toraja (South Sulawesi)");
        al_desc_tour.add("Tana Toraja Regency is one of the regencies located in South Sulawesi province, Indonesia. The capital of this regency is in Makale District. Tana Toraja has a land area of 2,054.30 km² and in 2021 had a population of 270,489 people with a density of 132 people/km².");
        al_price_tour.add(10000);
        al_location.add("Tana Toraja, South Sulawesi");

        //Kelimutu Lake (East Nusa Tenggara)
        al_img_tour.add("https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/danau-kelimutu.jpg");
        al_name_tour.add("Kelimutu Lake (East Nusa Tenggara)");
        al_desc_tour.add("Tiwu or Kelimutu Lake is divided into three parts that correspond to the colors of the water in the lake. Kelimutu Lake is located in Flores Island, East Nusa Tenggara Province. Mount Kelimutu with a height of 5,377 feet high towering stands in Pemo Village, Ende Regency has a mysterious story that will be discussed in this article.");
        al_price_tour.add(5000);
        al_location.add("Kelimutu Lake, East Nusa Tenggara");


        al_img_tour.add("https://royaltour.web.id/wp-content/uploads/2017/10/paket-wisata-kawah-ijen-2-hari-1-malam.jpg");
        al_name_tour.add("Ijen Crater (East Java)");
        al_desc_tour.add("Regarding the location of Ijen Crater, some call it Ijen Crater Banyuwangi, and some call it Ijen Crater Bondowoso. This is reasonable because Mount Ijen tourism is part of Baluran National Park which is located on the border of 2 areas, namely Licin District, Banyuwangi Regency and Klobang District, Bondowoso Regency, so it is synonymous with Banyuwangi and Bondowoso tourism.");
        al_price_tour.add(7500);
        al_location.add("Ijen Crater, East Java");

        // End of the data population

        RecycleViewAdapterProcess();
}

   

        private void RecycleViewAdapterProcess() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecycleViewAdapter adapter = new RecycleViewAdapter(al_img_tour, al_name_tour, al_desc_tour, al_price_tour, al_location, this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.bar_call_center:
                callCenter();
                return true;
            case R.id.bar_email:
                emailCenter();
                return true;
            case R.id.bar_loc:
                getLoc();
                return true;
            case R.id.bar_edit_user:
                editUser();
                return true;
            case R.id.bar_logout:
                getLogout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void callCenter() {
        alertDialog = new AlertDialog.Builder(Dashboard.this)
                .setIcon(android.R.drawable.ic_dialog_dialer)
                .setTitle("Call Center")
                .setMessage("\n082139860827")
                .setNeutralButton("Call", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Uri uri = Uri.parse("082139860827");
                        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                        intent.setData(Uri.fromParts("tel", String.valueOf(uri), null));

                        if (intent.resolveActivity(getPackageManager()) != null){
                            startActivity(intent);
                        }
                    }
                })
                .show();

    }
    private void emailCenter(){
        alertDialog = new AlertDialog.Builder(Dashboard.this)
                .setIcon(android.R.drawable.ic_dialog_email)
                .setTitle("Email")
                .setMessage("\nfihdanps@gmail.com")
                .setNeutralButton("Go to Email", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_SEND );
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"fihdanps@gmail.com"});
                        intent.putExtra(Intent.EXTRA_SUBJECT , "TES DULS YE BANG");
                        intent.putExtra(Intent.EXTRA_TEXT , "Travel App");
                        intent.setType("message/rfc822");
                        startActivity(Intent.createChooser(intent , "Choose Your Apps : "));
                    }
                })
                .show();

    }
    private void getLoc(){
        alertDialog = new AlertDialog.Builder(Dashboard.this)
                .setIcon(android.R.drawable.ic_dialog_info)
                .setTitle("Location")
                .setMessage("\nKota Madiun, Jawa Timur")
                .setNeutralButton("Go to Location", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Uri uri2 = Uri.parse("geo:0,0?q="+"Kota Madiun, Jawa Timur");
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri2);
                        mapIntent.setPackage("com.google.android.apps.maps");

                        if(mapIntent.resolveActivity(getPackageManager()) != null){
                            startActivity(mapIntent);
                        }
                    }
                })
                .show();

    }
    private void editUser(){
        Intent intent = new Intent(Dashboard.this, EditUser.class);
        startActivity(intent);

    }
    private void getLogout(){
        Intent intent = new Intent(Dashboard.this, LoginPage.class);
        startActivity(intent);
        finish();
    }
}