package app.philm.in.fragments;


import app.philm.in.lib.controllers.MovieController;
import app.philm.in.fragments.base.MovieGridFragment;

public class PopularMoviesFragment extends MovieGridFragment implements MovieController.SubUi {

    @Override
    public MovieController.MovieQueryType getMovieQueryType() {
        return MovieController.MovieQueryType.POPULAR;
    }

}
