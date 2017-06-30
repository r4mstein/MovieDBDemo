package ua.r4mstein.moviedbdemo.modules.people.details;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.reactivex.annotations.NonNull;
import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.data.models.response.PersonDetailsModel;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragment;
import ua.r4mstein.moviedbdemo.utills.Constants;

public class DetailsPeopleFragment extends BaseFragment<DetailsPeoplePresenter>
        implements DetailsPeoplePresenter.DetailsPeopleView {

    public static final String PERSON_ID = "person_id";

    private ImageView ivPoster;
    private TextView tvTitle, tvKnownAs, tvBiography, tvBirthday, tvDeathday, tvHomepage, tvPlaceOfBirth;

    @Override
    protected int getTitle() {
        return R.string.title_details_people;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_details_people;
    }

    @Override
    protected DetailsPeoplePresenter initPresenter() {
        return new DetailsPeoplePresenter();
    }

    @Override
    protected void findUI(View rootView) {
        ivPoster = (ImageView) rootView.findViewById(R.id.iv_poster_FDP);

        tvTitle = (TextView) rootView.findViewById(R.id.tv_title_FDP);
        tvKnownAs = (TextView) rootView.findViewById(R.id.tv_known_FDP);
        tvBiography = (TextView) rootView.findViewById(R.id.tv_biography_FDP);
        tvBirthday = (TextView) rootView.findViewById(R.id.tv_birthday_FDP);
        tvDeathday = (TextView) rootView.findViewById(R.id.tv_deathday_FDP);
        tvHomepage = (TextView) rootView.findViewById(R.id.tv_homepage_FDP);
        tvPlaceOfBirth = (TextView) rootView.findViewById(R.id.tv_place_FDP);
    }

    @Override
    protected void setupUI() {

    }

    public static DetailsPeopleFragment newInstance(long personId) {
        DetailsPeopleFragment fragment = new DetailsPeopleFragment();

        Bundle bundle = new Bundle();
        bundle.putLong(PERSON_ID, personId);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void fillData(PersonDetailsModel model) {
        tvTitle.setText(model.getName());

        fillPoster(ivPoster, model.getProfilePath());
        fillKnownAs(model);
        fillBiography(model);
        fillBirthday(model);
        fillDeathday(model);
        fillHomepage(model);
        fillPlace(model);
    }

    private void fillPoster(ImageView imageView, String url) {
        Picasso.with(getViewContext())
                .load(Constants.IMAGE_BASE_URL + url)
                .placeholder(R.drawable.main_logo)
                .error(R.drawable.main_logo)
                .into(imageView);
    }

    private void fillPlace(@NonNull PersonDetailsModel model) {
        String place = model.getPlaceOfBirth();
        if (place != null && !place.isEmpty()) {
            tvPlaceOfBirth.setText(place);
        } else {
            tvPlaceOfBirth.setText(R.string.error_movie_release_date);
        }
    }

    private void fillHomepage(@NonNull PersonDetailsModel model) {
        String homepage = model.getHomepage();
        if (homepage != null && !homepage.isEmpty()) {
            tvHomepage.setText(homepage);
        } else {
            tvHomepage.setVisibility(View.GONE);
        }
    }

    private void fillDeathday(@NonNull PersonDetailsModel model) {
        String deathday = model.getDeathday();
        if (deathday != null && !deathday.isEmpty()) {
            tvDeathday.setText(String.format("Deathday: %s", deathday));
        } else {
            tvDeathday.setVisibility(View.GONE);
        }
    }

    private void fillBirthday(@NonNull PersonDetailsModel model) {
        String birthday = model.getBirthday();
        if (birthday != null && !birthday.isEmpty()) {
            tvBirthday.setText(String.format("Birthday: %s", birthday));
        } else {
            tvBirthday.setVisibility(View.GONE);
        }
    }

    private void fillBiography(@NonNull PersonDetailsModel model) {
        String biography = model.getBiography();
        if (biography != null && !biography.isEmpty()) {
            tvBiography.setText(biography);
        } else {
            tvBiography.setVisibility(View.GONE);
        }
    }

    private void fillKnownAs(@NonNull PersonDetailsModel model) {
        List<String> knownAsList = model.getAlsoKnownAs();
        if (knownAsList != null && !knownAsList.isEmpty()) {
            for (String item : model.getAlsoKnownAs()) {
                tvKnownAs.append(item + "\n");
            }
        } else {
            tvKnownAs.setText(R.string.error_movie_release_date);
        }
    }
}
