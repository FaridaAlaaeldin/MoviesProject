package farida.moviesproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import farida.moviesproject.model.Movie;

public class Main2Activity extends AppCompatActivity {

    String title,overview,imageURL;
    TextView outTitle,outOverview;
    ImageView outImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle data=getIntent().getExtras();
        title=data.getString("title");
        overview=data.getString("overview");
        imageURL=data.getString("posterpath");

        outTitle=(TextView)findViewById(R.id.movieTitleOutput);
        outOverview=(TextView)findViewById(R.id.overviewOutput);
        outImage=(ImageView)findViewById(R.id.displayImage);

        outTitle.setText(title);
        outOverview.setText(overview);
        Picasso.with(getApplicationContext()).load(imageURL).into(outImage);

    }
}
