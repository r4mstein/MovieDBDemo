
package ua.r4mstein.moviedbdemo.data.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonDetailsModel {

    @SerializedName("adult")
    private Boolean mAdult;
    @SerializedName("also_known_as")
    private List<String> mAlsoKnownAs;
    @SerializedName("biography")
    private String mBiography;
    @SerializedName("birthday")
    private String mBirthday;
    @SerializedName("deathday")
    private String mDeathday;
    @SerializedName("gender")
    private Long mGender;
    @SerializedName("homepage")
    private String mHomepage;
    @SerializedName("id")
    private Long mId;
    @SerializedName("imdb_id")
    private String mImdbId;
    @SerializedName("name")
    private String mName;
    @SerializedName("place_of_birth")
    private String mPlaceOfBirth;
    @SerializedName("popularity")
    private Double mPopularity;
    @SerializedName("profile_path")
    private String mProfilePath;

    public List<String> getAlsoKnownAs() {
        return mAlsoKnownAs;
    }

    public String getBiography() {
        return mBiography;
    }

    public String getBirthday() {
        return mBirthday;
    }

    public String getDeathday() {
        return mDeathday;
    }

    public String getHomepage() {
        return mHomepage;
    }

    public Long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getPlaceOfBirth() {
        return mPlaceOfBirth;
    }

    public String getProfilePath() {
        return mProfilePath;
    }

}
