
package ua.r4mstein.moviedbdemo.data.models.response;

import com.google.gson.annotations.SerializedName;

public class UserModel {

    @SerializedName("avatar")
    private Avatar mAvatar;
    @SerializedName("id")
    private Long mId;
    @SerializedName("include_adult")
    private Boolean mIncludeAdult;
    @SerializedName("iso_3166_1")
    private String mIso31661;
    @SerializedName("iso_639_1")
    private String mIso6391;
    @SerializedName("name")
    private String mName;
    @SerializedName("username")
    private String mUsername;


    public class Avatar {

        @SerializedName("gravatar")
        private Gravatar mGravatar;

        public Gravatar getGravatar() {
            return mGravatar;
        }

        public void setGravatar(Gravatar gravatar) {
            mGravatar = gravatar;
        }

        public class Gravatar {

            @SerializedName("hash")
            private String mHash;

            public String getHash() {
                return mHash;
            }

            public void setHash(String hash) {
                mHash = hash;
            }

        }
    }

    public Avatar getAvatar() {
        return mAvatar;
    }

    public void setAvatar(Avatar avatar) {
        mAvatar = avatar;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Boolean getIncludeAdult() {
        return mIncludeAdult;
    }

    public void setIncludeAdult(Boolean includeAdult) {
        mIncludeAdult = includeAdult;
    }

    public String getIso31661() {
        return mIso31661;
    }

    public void setIso31661(String iso31661) {
        mIso31661 = iso31661;
    }

    public String getIso6391() {
        return mIso6391;
    }

    public void setIso6391(String iso6391) {
        mIso6391 = iso6391;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

}
