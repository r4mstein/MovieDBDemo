
package ua.r4mstein.moviedbdemo.data.models.response.popular_people;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularPeopleItem {

    @SerializedName("adult")
    private Boolean mAdult;
    @SerializedName("id")
    private Long mId;
    @SerializedName("known_for")
    private List<KnownFor> mKnownFor;
    @SerializedName("name")
    private String mName;
    @SerializedName("popularity")
    private Double mPopularity;
    @SerializedName("profile_path")
    private String mProfilePath;

    public Boolean getAdult() {
        return mAdult;
    }

    public void setAdult(Boolean adult) {
        mAdult = adult;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public List<KnownFor> getKnownFor() {
        return mKnownFor;
    }

    public void setKnownFor(List<KnownFor> knownFor) {
        mKnownFor = knownFor;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Double getPopularity() {
        return mPopularity;
    }

    public void setPopularity(Double popularity) {
        mPopularity = popularity;
    }

    public String getProfilePath() {
        return mProfilePath;
    }

    public void setProfilePath(String profilePath) {
        mProfilePath = profilePath;
    }

}
