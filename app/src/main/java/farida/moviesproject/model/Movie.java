package farida.moviesproject.model;

import java.io.Serializable;

/**
 * Created by Farida Alaaeldin on 19-Jul-17.
 */
public class Movie
{
        private String title;
        private String posterPath;
        private String overview;



        public Movie()
        {

        }

        public void setName(String name)
        {
            this.title=name;
        }

        public void setPosterPath(String rate)
        {
            this.posterPath=rate;
        }

        public void setOverView(String desc)
        {
            this.overview=desc;
        }

        public String getName()
        {
            return title;
        }

        public String getPosterPath()
        {
            return posterPath;
        }

        public String getOverView()
        {
            return  overview;
        }

}


