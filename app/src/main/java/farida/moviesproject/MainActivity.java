package farida.moviesproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import farida.moviesproject.adapter.Adapter;
import farida.moviesproject.model.Movie;
import farida.moviesproject.adapter.RecyclerViewClickListener;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickListener{

    Adapter adapter;
    String sector="http://image.tmdb.org/t/p/w342/";
    List<Movie> myMovies;
    RecyclerView display;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display=(RecyclerView)findViewById(R.id.display);
        display.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));



        myMovies=new ArrayList<>();

        adapter=new Adapter(getApplicationContext(),myMovies,this);
        display.setAdapter(adapter);

        requestQueue= Volley.newRequestQueue(getApplicationContext());

       // display.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL));

        String url="https://api.themoviedb.org/3/discover/movie?api_key=69f8d44407d7b73a4103add4c76fccb6";
        getMovieData(url);

    }



  //  myAdapter = new MyRecyclerViewAdapter(context, this);


    void getMovieData(String url)
    {
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray fileinput=response.getJSONArray("results");
                    for(int i=0;i<fileinput.length();i++){
                        JSONObject temp=fileinput.getJSONObject(i);
                        Movie movie=new Movie();
                        movie.setName(temp.getString("title"));
                        movie.setPosterPath(sector+temp.getString("poster_path"));
                        movie.setOverView(temp.getString("overview"));
                        myMovies.add(movie);
                    }
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

        requestQueue.add(request);

    }
    @Override
    public void RecyclerViewListClicked(int position) {
        Intent myintent=new Intent(MainActivity.this,Main2Activity.class);
        myintent.putExtra("title",myMovies.get(position).getName());
        myintent.putExtra("overview",myMovies.get(position).getOverView());
        myintent.putExtra("posterpath",myMovies.get(position).getPosterPath());

        startActivity(myintent);
    }

}
